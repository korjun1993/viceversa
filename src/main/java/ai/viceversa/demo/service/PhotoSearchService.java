package ai.viceversa.demo.service;

import static ai.viceversa.demo.domain.QPhoto.*;
import static ai.viceversa.demo.domain.QPhotoSearchKeyword.*;
import static ai.viceversa.demo.domain.QTitle.*;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import ai.viceversa.demo.dto.PhotoDetailDto;
import ai.viceversa.demo.dto.PhotoSearchRequestDto;
import ai.viceversa.demo.dto.PhotoSearchResponseDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhotoSearchService {
	private final JPAQueryFactory jpaQueryFactory;

	public PhotoSearchResponseDto findAll(PhotoSearchRequestDto dto) {
		List<PhotoDetailDto> searchResult = jpaQueryFactory.selectFrom(photo)
			.innerJoin(photo.title, title)
			.leftJoin(photo.searchKeywords, photoSearchKeyword)
			.where(eqPhotographer(dto.photographer()), eqTitle(dto.photoTitle()), eqKeyword(dto.keyword()))
			.fetch()
			.stream().map(PhotoDetailDto::of)
			.toList();

		return new PhotoSearchResponseDto(searchResult, searchResult.size());
	}

	private BooleanBuilder eqTitle(String photoTitle) {
		return nullSafeBuilder(() -> title.id.eq(photoTitle));
	}

	private BooleanBuilder eqPhotographer(String photographer) {
		return nullSafeBuilder(() -> photo.photographer.eq(photographer));
	}

	private BooleanBuilder eqKeyword(String keyword) {
		return nullSafeBuilder(() -> photoSearchKeyword.searchKeyword.id.eq(keyword));
	}

	public static BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
		try {
			return new BooleanBuilder(f.get());
		} catch (IllegalArgumentException e) {
			return new BooleanBuilder();
		}
	}
}
