package ai.viceversa.demo.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import ai.viceversa.demo.api.OpenApiClient;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Slf4j
@Configuration
public class OpenApiConfiguration {
	private static final String DATA_API_URL = "https://apis.data.go.kr/B551011/PhotoGalleryService1?MobileOS=ETC&MobileApp=TEST&_type={dataType}&&serviceKey={secretKey}";
	private static final int RETRY_COUNT = 3;
	private static final int BACKOFF_DELAY_SECONDS = 5;

	@Bean
	public OpenApiClient photoGalleryHttpApiClient(OpenApiProperties openApiProperties) {
		Map<String, Object> defaultUrlVariables = new HashMap<>();
		defaultUrlVariables.put("secretKey", openApiProperties.secretKey());
		defaultUrlVariables.put("dataType", openApiProperties.dataType());

		WebClient webClient = WebClient.builder()
			.filter(logRequest())
			.filter(retryFilter())
			.baseUrl(DATA_API_URL)
			.defaultUriVariables(defaultUrlVariables)
			.build();

		HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(
				WebClientAdapter.forClient(webClient))
			.blockTimeout(Duration.ofSeconds(openApiProperties.timeout()))
			.build();

		return httpServiceProxyFactory.createClient(OpenApiClient.class);
	}

	@Bean
	public ExchangeFilterFunction logRequest() {
		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
			log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
			clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
			return Mono.just(clientRequest);
		});
	}

	private ExchangeFilterFunction retryFilter() {
		return (request, next) ->
			next.exchange(request)
				.retryWhen(
					Retry.fixedDelay(RETRY_COUNT, Duration.ofSeconds(BACKOFF_DELAY_SECONDS))
						.doAfterRetry(retrySignal -> log.warn("Retrying")));
	}
}
