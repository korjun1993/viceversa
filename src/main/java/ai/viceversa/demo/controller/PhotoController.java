package ai.viceversa.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ai.viceversa.demo.dto.PhotoSearchRequestDto;
import ai.viceversa.demo.dto.PhotoSearchResponseDto;
import ai.viceversa.demo.service.PhotoSearchService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PhotoController {
	private final PhotoSearchService photoSearchService;

	@GetMapping("/photos")
	public PhotoSearchResponseDto getPhotos(@RequestBody PhotoSearchRequestDto dto) {
		return photoSearchService.findAll(dto);
	}
}
