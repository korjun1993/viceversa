package ai.viceversa.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ai.viceversa.demo.api.PhotoGalleryHttpApiClient;
import ai.viceversa.demo.dto.PhotoGalleryFetchResponseDto;
import ai.viceversa.demo.extension.ResponseUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhotoGalleryFetchService {
	private final PhotoGalleryHttpApiClient photoGalleryHttpApiClient;
	private final ObjectMapper objectMapper;

	public List<PhotoGalleryFetchResponseDto> fetch(int numOfRows, int pageNo) throws JsonProcessingException {
		String body = ResponseUtils.body(photoGalleryHttpApiClient.getPhotoList(numOfRows, pageNo));
		return objectMapper.readValue(body,
			objectMapper.getTypeFactory().constructCollectionType(List.class, PhotoGalleryFetchResponseDto.class));
	}
}
