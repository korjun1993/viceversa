package ai.viceversa.demo.api;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import reactor.core.publisher.Flux;

public interface OpenApiClient {
	@GetExchange("/galleryList1")
	Flux<String> getPhotoList(@RequestParam int numOfRows, @RequestParam int pageNo);

	@GetExchange("/galleryDetailList1")
	Flux<String> getPhotoDetail(@RequestParam String title, @RequestParam int numOfRows, @RequestParam int pageNo);
}
