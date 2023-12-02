package ai.viceversa.demo.mapper;

import java.util.Arrays;
import java.util.List;

import ai.viceversa.demo.domain.Photo;
import ai.viceversa.demo.domain.PhotoType;
import ai.viceversa.demo.domain.SearchKeyword;
import ai.viceversa.demo.domain.Title;
import ai.viceversa.demo.domain.YearMonth;
import ai.viceversa.demo.dto.ItemDto;
import ai.viceversa.demo.extension.LocalDateTimeUtils;

public class PhotoMapper {
	private static final String SEARCH_KEYWORD_DELIMITER = ", ";

	public static Photo toPhoto(ItemDto dto) {
		Photo photo = buildPhoto(dto);
		PhotoType photoType = buildPhotoType(dto);
		Title title = buildTitle(dto);
		List<SearchKeyword> searchKeywords = buildSearchKeyword(dto);
		photo.setPhotoType(photoType);
		photo.setTitle(title);
		photo.addSearchKeywords(searchKeywords);
		return photo;
	}

	private static Photo buildPhoto(ItemDto dto) {
		return Photo.builder()
			.contentId(dto.galContentId())
			.imageUrl(dto.galWebImageUrl())
			.createdTime(LocalDateTimeUtils.toLocalDateTime(dto.galCreatedtime()))
			.modifiedTime(LocalDateTimeUtils.toLocalDateTime(dto.galModifiedtime()))
			.yearMonth(YearMonth.of(dto.galPhotographyMonth()))
			.location(dto.galPhotographyLocation())
			.photographer(dto.galPhotographer())
			.build();
	}

	private static Title buildTitle(ItemDto dto) {
		return Title.builder()
			.name(dto.galTitle())
			.build();
	}

	private static PhotoType buildPhotoType(ItemDto dto) {
		return PhotoType.builder()
			.contentTypeId(dto.galContentTypeId())
			.build();
	}

	private static List<SearchKeyword> buildSearchKeyword(ItemDto dto) {
		return Arrays.stream(dto.galSearchKeyword().split(SEARCH_KEYWORD_DELIMITER))
			.map(SearchKeyword::new)
			.toList();
	}
}
