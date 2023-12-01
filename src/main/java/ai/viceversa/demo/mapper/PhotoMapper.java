package ai.viceversa.demo.mapper;

import java.util.Arrays;
import java.util.List;

import ai.viceversa.demo.domain.Photo;
import ai.viceversa.demo.domain.PhotoType;
import ai.viceversa.demo.domain.SearchKeyword;
import ai.viceversa.demo.domain.YearMonth;
import ai.viceversa.demo.dto.PhotoFetchResponseDto;
import ai.viceversa.demo.extension.LocalDateTimeUtils;

public class PhotoMapper {
	private static final String SEARCH_KEYWORD_DELIMITER = ", ";

	public static Photo toPhoto(PhotoFetchResponseDto dto) {
		Photo photo = buildPhoto(dto);
		PhotoType photoType = buildPhotoType(dto);
		List<SearchKeyword> searchKeywords = buildSearchKeyword(dto);
		photo.setPhotoType(photoType);
		photo.addSearchKeywords(searchKeywords);
		return photo;
	}

	private static Photo buildPhoto(PhotoFetchResponseDto dto) {
		return Photo.builder()
			.id(dto.galContentId())
			.title(dto.galTitle())
			.imageUrl(dto.galWebImageUrl())
			.createdTime(LocalDateTimeUtils.toLocalDateTime(dto.galCreatedtime()))
			.modifiedTime(LocalDateTimeUtils.toLocalDateTime(dto.galModifiedtime()))
			.month(YearMonth.of(dto.galPhotographyMonth()))
			.location(dto.galPhotographyLocation())
			.photographer(dto.galPhotographer())
			.build();
	}

	private static PhotoType buildPhotoType(PhotoFetchResponseDto dto) {
		return PhotoType.builder()
			.id(dto.galContentTypeId())
			.build();
	}

	private static List<SearchKeyword> buildSearchKeyword(PhotoFetchResponseDto dto) {
		return Arrays.stream(dto.galSearchKeyword().split(SEARCH_KEYWORD_DELIMITER))
			.map(SearchKeyword::new)
			.toList();
	}
}
