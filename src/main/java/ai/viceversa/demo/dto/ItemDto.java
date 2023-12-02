package ai.viceversa.demo.dto;

import lombok.Builder;

@Builder
public record ItemDto(
	Long galContentId,
	Long galContentTypeId,
	String galTitle,
	String galWebImageUrl,
	String galCreatedtime,
	String galModifiedtime,
	String galPhotographyMonth,
	String galPhotographyLocation,
	String galPhotographer,
	String galSearchKeyword
) {
}
