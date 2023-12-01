package ai.viceversa.demo.dto;

public record PhotoGalleryFetchResponseDto(
	String galContentId,
	String galContentTypeId,
	String galTitle,
	String webImageUrl,
	String galCreatedtime,
	String galModifiedtime,
	String galPhotographyMonth,
	String galPhotographyLocation,
	String galPhotographer,
	String galSearchKeyword
) {
}
