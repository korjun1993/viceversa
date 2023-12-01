package ai.viceversa.demo.api;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface PhotoGalleryHttpApiClient {
	@GetExchange("/galleryList1")
	String getPhotoList(@RequestParam int numOfRows, @RequestParam int pageNo);

	@GetExchange("/galleryDetailList1")
	String getPhotoDetail(@RequestParam String title, @RequestParam int numOfRows, @RequestParam int pageNo);
}
