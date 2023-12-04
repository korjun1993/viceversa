package ai.viceversa.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

import ai.viceversa.demo.domain.Photo;
import lombok.Builder;

@Builder
public record PhotoDetailDto(
	Long id,
	Long typeId,
	String title,
	String imageUrl,
	LocalDateTime createdTime,
	LocalDateTime modifiedTime,
	String month,
	String location,
	String photographer,
	List<String> searchKeywords
) {
	public static PhotoDetailDto of(Photo photo) {
		return PhotoDetailDto.builder()
			.id(photo.getId())
			.typeId(photo.getPhotoType().getId())
			.title(photo.getTitle().getId())
			.imageUrl(photo.getImageUrl())
			.photographer(photo.getPhotographer())
			.createdTime(photo.getCreatedTime())
			.modifiedTime(photo.getModifiedTime())
			.month(photo.getYearMonth().toString())
			.location(photo.getLocation())
			.searchKeywords(photo.getSearchKeywords().stream().map(x -> x.getSearchKeyword().getId()).toList())
			.build();
	}
}
