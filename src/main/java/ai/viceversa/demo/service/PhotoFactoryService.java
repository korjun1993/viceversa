package ai.viceversa.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ai.viceversa.demo.domain.Photo;
import ai.viceversa.demo.domain.PhotoType;
import ai.viceversa.demo.domain.SearchKeyword;
import ai.viceversa.demo.domain.Title;
import ai.viceversa.demo.domain.YearMonth;
import ai.viceversa.demo.dto.ItemDto;
import ai.viceversa.demo.extension.LocalDateTimeUtils;
import ai.viceversa.demo.repository.PhotoTypeRepository;
import ai.viceversa.demo.repository.SearchKeywordRepository;
import ai.viceversa.demo.repository.TitleRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PhotoFactoryService {
	private static final String SEARCH_KEYWORD_DELIMITER = ", ";
	private final PhotoTypeRepository photoTypeRepository;
	private final TitleRepository titleRepository;
	private final SearchKeywordRepository searchKeywordRepository;

	public Photo toPhoto(ItemDto dto) {
		Photo photo = buildPhoto(dto);
		PhotoType photoType = buildPhotoType(dto);
		Title title = buildTitle(dto);
		List<SearchKeyword> searchKeywords = buildSearchKeyword(dto);
		photo.setPhotoType(photoType);
		photo.setTitle(title);
		photo.addSearchKeywords(searchKeywords);
		return photo;
	}

	public Photo buildPhoto(ItemDto dto) {
		return Photo.builder()
			.id(dto.galContentId())
			.imageUrl(dto.galWebImageUrl())
			.createdTime(LocalDateTimeUtils.toLocalDateTime(dto.galCreatedtime()))
			.modifiedTime(LocalDateTimeUtils.toLocalDateTime(dto.galModifiedtime()))
			.yearMonth(YearMonth.of(dto.galPhotographyMonth()))
			.location(dto.galPhotographyLocation())
			.photographer(dto.galPhotographer())
			.build();
	}

	public Title buildTitle(ItemDto dto) {
		return titleRepository.findById(dto.galTitle()).orElseGet(() -> new Title(dto.galTitle()));
	}

	public PhotoType buildPhotoType(ItemDto dto) {
		return photoTypeRepository.findById(dto.galContentTypeId())
			.orElseGet(() -> new PhotoType(dto.galContentTypeId()));
	}

	public List<SearchKeyword> buildSearchKeyword(ItemDto dto) {
		return Arrays.stream(dto.galSearchKeyword().split(SEARCH_KEYWORD_DELIMITER)).distinct()
			.map(keyword ->
				searchKeywordRepository.findById(keyword).orElseGet(() -> new SearchKeyword(keyword))
			).toList();
	}
}
