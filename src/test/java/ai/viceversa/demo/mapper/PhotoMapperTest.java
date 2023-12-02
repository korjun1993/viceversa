package ai.viceversa.demo.mapper;

import java.time.LocalDateTime;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ai.viceversa.demo.domain.Photo;
import ai.viceversa.demo.domain.YearMonth;
import ai.viceversa.demo.dto.PhotoFetchResponseDto;

class PhotoMapperTest {

	@Test
	@DisplayName("PhotoFetchResponseDto 로부터 Photo, PhotoType, SearchKeyword 엔티티를 생성하고 연관관계를 맺을 수 있다")
	void test1() {
		// given
		PhotoFetchResponseDto dto = PhotoFetchResponseDto.builder()
			.galContentId(3047209L)
			.galContentTypeId(17L)
			.galTitle("서상돈 고택")
			.galWebImageUrl("http://tong.visitkorea.or.kr/cms2/website/09/3047209.jpg")
			.galCreatedtime("20231123145848")
			.galModifiedtime("20231130233811")
			.galPhotographyMonth("202310")
			.galPhotographyLocation("대구광역시 중구 계산동2가")
			.galPhotographer("김효서")
			.galSearchKeyword("서상돈 고택, 대구광역시 중구, 전통한옥, 대구 근대골목투어, 대구 중구 근대로의 여행, 근대문화골목, 10월 추천여행지")
			.build();

		// when
		Photo actual = PhotoMapper.toPhoto(dto);

		// then
		SoftAssertions.assertSoftly(softAssertions -> {
			softAssertions.assertThat(actual.getContentId()).isEqualTo(3047209L);
			softAssertions.assertThat(actual.getPhotoType().getContentTypeId()).isEqualTo(17L);
			softAssertions.assertThat(actual.getTitle()).isEqualTo("서상돈 고택");
			softAssertions.assertThat(actual.getImageUrl())
				.isEqualTo("http://tong.visitkorea.or.kr/cms2/website/09/3047209.jpg");
			softAssertions.assertThat(actual.getCreatedTime()).isEqualTo(LocalDateTime.of(2023, 11, 23, 14, 58, 48));
			softAssertions.assertThat(actual.getModifiedTime()).isEqualTo(LocalDateTime.of(2023, 11, 30, 23, 38, 11));
			softAssertions.assertThat(actual.getYearMonth()).isEqualTo(YearMonth.of(2023, 10));
			softAssertions.assertThat(actual.getLocation()).isEqualTo("대구광역시 중구 계산동2가");
			softAssertions.assertThat(actual.getPhotographer()).isEqualTo("김효서");
			softAssertions.assertThat(actual.getSearchKeywords().get(0).getSearchKeyword().getName()).isEqualTo("서상돈 고택");
			softAssertions.assertThat(actual.getSearchKeywords().get(1).getSearchKeyword().getName())
				.isEqualTo("대구광역시 중구");
			softAssertions.assertThat(actual.getSearchKeywords().get(2).getSearchKeyword().getName()).isEqualTo("전통한옥");
			softAssertions.assertThat(actual.getSearchKeywords().get(3).getSearchKeyword().getName())
				.isEqualTo("대구 근대골목투어");
			softAssertions.assertThat(actual.getSearchKeywords().get(4).getSearchKeyword().getName())
				.isEqualTo("대구 중구 근대로의 여행");
			softAssertions.assertThat(actual.getSearchKeywords().get(5).getSearchKeyword().getName()).isEqualTo("근대문화골목");
			softAssertions.assertThat(actual.getSearchKeywords().get(6).getSearchKeyword().getName())
				.isEqualTo("10월 추천여행지");
		});
	}
}
