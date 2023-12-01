package ai.viceversa.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ai.viceversa.demo.api.PhotoGalleryHttpApiClient;
import ai.viceversa.demo.dto.PhotoFetchResponseDto;
import ai.viceversa.demo.extension.ResponseUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoFetchService {
	private final PhotoGalleryHttpApiClient photoGalleryHttpApiClient;
	private final ObjectMapper objectMapper;

	public List<PhotoFetchResponseDto> fetch(int numOfRows, int pageNo) {
		String body = ResponseUtils.body(photoGalleryHttpApiClient.getPhotoList(numOfRows, pageNo));
		try {
			return objectMapper.readValue(body,
				objectMapper.getTypeFactory().constructCollectionType(List.class, PhotoFetchResponseDto.class));
		} catch (JsonProcessingException e) {
			log.error("Open API 데이터(JSON) → 파싱 오류 발생 = {}", body, e);
			throw new RuntimeException(e);
		}
	}
}
