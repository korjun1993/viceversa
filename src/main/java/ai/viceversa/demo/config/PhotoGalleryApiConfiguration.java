package ai.viceversa.demo.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import ai.viceversa.demo.api.PhotoGalleryHttpApiClient;

@Configuration
public class PhotoGalleryApiConfiguration {
	private static final String DATA_API_URL = "https://apis.data.go.kr/B551011/PhotoGalleryService1?MobileOS=ETC&MobileApp=TEST&_type={dataType}&&serviceKey={secretKey}";

	@Bean
	public PhotoGalleryHttpApiClient photoGalleryHttpApiClient(OpenApiProperties openApiProperties) {
		Map<String, Object> defaultUrlVariables = new HashMap<>();
		defaultUrlVariables.put("secretKey", openApiProperties.secretKey());
		defaultUrlVariables.put("dataType", openApiProperties.dataType());

		WebClient webClient = WebClient.builder()
			.baseUrl(DATA_API_URL)
			.defaultUriVariables(defaultUrlVariables)
			.build();

		HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builder(
				WebClientAdapter.forClient(webClient))
			.blockTimeout(Duration.ofSeconds(openApiProperties.timeout()))
			.build();

		return httpServiceProxyFactory.createClient(PhotoGalleryHttpApiClient.class);
	}
}
