package ai.viceversa.demo.dto;

import java.util.List;

public record PhotoSearchResponseDto(
	List<PhotoDetailDto> items,
	int totalCount
) {

}
