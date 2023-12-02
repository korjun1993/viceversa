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

@Slf4j
@Configuration
public class OpenApiConfiguration {
	private static final String DATA_API_URL = "https://apis.data.go.kr/B551011/PhotoGalleryService1?MobileOS=ETC&MobileApp=TEST&_type={dataType}&&serviceKey={secretKey}";

	@Bean
	public OpenApiClient photoGalleryHttpApiClient(OpenApiProperties openApiProperties) {
		Map<String, Object> defaultUrlVariables = new HashMap<>();
		defaultUrlVariables.put("secretKey", openApiProperties.secretKey());
		defaultUrlVariables.put("dataType", openApiProperties.dataType());

		WebClient webClient = WebClient.builder()
			.filter(logRequest())
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
}
