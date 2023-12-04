package ai.viceversa.demo.service;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import ai.viceversa.demo.domain.Photo;
import ai.viceversa.demo.domain.PhotoType;
import ai.viceversa.demo.domain.Title;
import ai.viceversa.demo.domain.YearMonth;
import ai.viceversa.demo.dto.ItemDto;
import ai.viceversa.demo.repository.PhotoTypeRepository;
import ai.viceversa.demo.repository.TitleRepository;

@Transactional
@SpringBootTest
class PhotoFactoryServiceTest {

	@Autowired
	private PhotoFactoryService sut;

	@Autowired
	private PhotoTypeRepository photoTypeRepository;

	@Autowired
	private TitleRepository titleRepository;

	private static final ItemDto fixture = ItemDto.builder()
		.galContentId(3047209L)
		.galContentTypeId(157L)
		.galTitle("롯데월드")
		.galWebImageUrl("http://tong.visitkorea.or.kr/cms2/website/09/3047209.jpg")
		.galCreatedtime("20231123145848")
		.galModifiedtime("20231130233811")
		.galPhotographyMonth("202310")
		.galPhotographyLocation("서울특별지 잠실 롯데월드")
		.galPhotographer("김효서")
		.galSearchKeyword("롯데월드 아틀란티스 열차, 12월 가족 나들이")
		.build();

	@Test
	@DisplayName("ItemDto 로부터 Photo, PhotoType, SearchKeyword 엔티티를 생성하고 연관관계를 맺을 수 있다")
	void test1() {
		// when
		Photo actual = sut.toPhoto(fixture);

		// then
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(actual.getId()).isEqualTo(3047209L);
			softAssertions.assertThat(actual.getPhotoType().getId()).isEqualTo(157L);
			softAssertions.assertThat(actual.getTitle().getId()).isEqualTo("롯데월드");
			softAssertions.assertThat(actual.getImageUrl())
				.isEqualTo("http://tong.visitkorea.or.kr/cms2/website/09/3047209.jpg");
			softAssertions.assertThat(actual.getCreatedTime()).isEqualTo(LocalDateTime.of(2023, 11, 23, 14, 58, 48));
			softAssertions.assertThat(actual.getModifiedTime()).isEqualTo(LocalDateTime.of(2023, 11, 30, 23, 38, 11));
			softAssertions.assertThat(actual.getYearMonth()).isEqualTo(YearMonth.of(2023, 10));
			softAssertions.assertThat(actual.getLocation()).isEqualTo("서울특별지 잠실 롯데월드");
			softAssertions.assertThat(actual.getPhotographer()).isEqualTo("김효서");
			softAssertions.assertThat(actual.getSearchKeywords().get(0).getSearchKeyword().getId()).isEqualTo("롯데월드 아틀란티스 열차");
			softAssertions.assertThat(actual.getSearchKeywords().get(1).getSearchKeyword().getId())
				.isEqualTo("12월 가족 나들이");
		});
	}

	@Test
	@DisplayName("ItemDto 로부터 Photo, PhotoType, SearchKeyword 엔티티를 생성할 때, 이미 존재하는 엔티티가 있다면 기존의 엔티티로 연관관계를 맺는다")
	void test2() {
		// given
		PhotoType oldPhotoType = photoTypeRepository.save(new PhotoType(157L));
		Title oldTitle = titleRepository.save(new Title("롯데월드"));

		// when
		Photo actual = sut.toPhoto(fixture);

		// then
		Assertions.assertThat(actual.getPhotoType()).isEqualTo(oldPhotoType);
		Assertions.assertThat(actual.getTitle()).isEqualTo(oldTitle);
	}
}
