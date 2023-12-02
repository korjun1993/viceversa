package ai.viceversa.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import ai.viceversa.demo.api.OpenApiClient;
import ai.viceversa.demo.dto.PhotoFetchResponseDto;
import ai.viceversa.demo.extension.ParsingUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoFetchService {
	private final OpenApiClient openApiClient;
	private final ObjectMapper objectMapper;

	public List<PhotoFetchResponseDto> fetchList(int numOfRows, int pageNo) {
		return ParsingUtils.readValue(objectMapper, openApiClient.getPhotoList(numOfRows, pageNo));
	}
}
