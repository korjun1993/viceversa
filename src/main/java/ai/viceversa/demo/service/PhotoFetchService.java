package ai.viceversa.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import ai.viceversa.demo.api.OpenApiClient;
import ai.viceversa.demo.dto.ItemDto;
import ai.viceversa.demo.dto.PhotoFetchResponseDto;
import ai.viceversa.demo.extension.ParsingUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhotoFetchService {
	private final OpenApiClient openApiClient;
	private final ObjectMapper objectMapper;

	public Flux<PhotoFetchResponseDto> fetchList(int numOfRows, int pageNo) {
		return openApiClient.getPhotoList(numOfRows, pageNo).mapNotNull(
			response -> ParsingUtils.readValue(objectMapper, response)
		);
	}

	public Flux<PhotoFetchResponseDto> fetchDetail(String title, int numOfRows, int pageNo) {
		return openApiClient.getPhotoDetail(title, numOfRows, pageNo)
			.mapNotNull(response -> ParsingUtils.readValue(objectMapper, response));
	}

	public Mono<List<PhotoFetchResponseDto>> fetchDetailsJoinWithTitles(int fetchCountOfTitles, int PageNoOfTitles, int fetchCountOfDetails, int pageNoOfDetails) {
		return fetchList(fetchCountOfTitles, PageNoOfTitles)
			.map(PhotoFetchResponseDto::items)
			.flatMap(Flux::fromIterable)
			.map(ItemDto::galTitle)
			.flatMap(title -> fetchDetail(title, fetchCountOfDetails, pageNoOfDetails))
			.collectList();
	}
}
