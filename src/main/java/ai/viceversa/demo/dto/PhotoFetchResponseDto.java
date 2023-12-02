package ai.viceversa.demo.dto;

import java.util.List;

import lombok.Builder;

@Builder
public record PhotoFetchResponseDto(
	List<ItemDto> items,
	int totalCount
) {
}

