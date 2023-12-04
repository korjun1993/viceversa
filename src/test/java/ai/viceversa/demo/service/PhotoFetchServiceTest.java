package ai.viceversa.demo.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;

import ai.viceversa.demo.OpenApiMock;
import ai.viceversa.demo.api.OpenApiClient;
import ai.viceversa.demo.dto.ItemDto;
import ai.viceversa.demo.dto.PhotoFetchResponseDto;

@ExtendWith(MockitoExtension.class)
class PhotoFetchServiceTest {
	private final OpenApiClient openApiClient = OpenApiMock.server;
	private final PhotoFetchService sut = new PhotoFetchService(openApiClient, new ObjectMapper());

	@Test
	@DisplayName("오픈 API를 통해 사진 목록을 조회한다")
	void test1() {
		var actual = sut.fetchList(2, 1).blockFirst();
		assertThat(actual).isNotNull();
		assertThat(actual.items().size()).isEqualTo(2);
		assertThat(actual.items().get(0).galTitle()).isEqualTo("청설모");
		assertThat(actual.items().get(1).galTitle()).isEqualTo("이태원 거리");
	}

	@Test
	@DisplayName("오픈 API를 통해 제목(=청설모)으로 사진 상세 정보 3개를 조회한다")
	void test2() {
		var actual = sut.fetchDetail("청설모", 3, 1).blockFirst();
		assertThat(actual).isEqualTo(
			PhotoFetchResponseDto.builder()
				.items(List.of(
					ItemDto.builder()
						.galContentId(1002144L)
						.galContentTypeId(17L)
						.galTitle("청설모")
						.galWebImageUrl("http://tong.visitkorea.or.kr/cms2/website/44/1002144.jpg")
						.galCreatedtime("20100420024817")
						.galModifiedtime("20150818231341")
						.galPhotographyMonth("201004")
						.galPhotographyLocation("서울 경복궁")
						.galPhotographer("한국관광공사 김지호")
						.galSearchKeyword("청설모, 동물")
						.build(),
					ItemDto.builder()
						.galContentId(1002145L)
						.galContentTypeId(17L)
						.galTitle("청설모")
						.galWebImageUrl("http://tong.visitkorea.or.kr/cms2/website/44/1002145.jpg")
						.galCreatedtime("20100420024817")
						.galModifiedtime("20150818231341")
						.galPhotographyMonth("201004")
						.galPhotographyLocation("수리산")
						.galPhotographer("직장인동호회 김창윤")
						.galSearchKeyword("청설모, 동물")
						.build(),
					ItemDto.builder()
						.galContentId(1002146L)
						.galContentTypeId(17L)
						.galTitle("청설모")
						.galWebImageUrl("http://tong.visitkorea.or.kr/cms2/website/44/1002146.jpg")
						.galCreatedtime("20100420024817")
						.galModifiedtime("20150818231341")
						.galPhotographyMonth("201004")
						.galPhotographyLocation("관악산")
						.galPhotographer("경기관광공사 김호준")
						.galSearchKeyword("청설모, 동물")
						.build()))
				.totalCount(10)
				.build());
	}

	@Test
	@DisplayName("오픈 API를 통해 2개의 제목과 각 제목별로 최대 3개의 사진 상세 정보를 조회한다")
	void test3() {
		var actual = sut.fetchDetailsJoinWithTitles(2, 0, 3, 0).block();
		assertThat(actual).isNotNull();
		assertThat(actual.size()).isEqualTo(2);
		assertThat(actual.get(0)).isEqualTo(
			PhotoFetchResponseDto.builder()
				.items(
					List.of(
						ItemDto.builder()
							.galContentId(1002144L)
							.galContentTypeId(17L)
							.galTitle("청설모")
							.galWebImageUrl("http://tong.visitkorea.or.kr/cms2/website/44/1002144.jpg")
							.galCreatedtime("20100420024817")
							.galModifiedtime("20150818231341")
							.galPhotographyMonth("201004")
							.galPhotographyLocation("서울 경복궁")
							.galPhotographer("한국관광공사 김지호")
							.galSearchKeyword("청설모, 동물")
							.build(),
						ItemDto.builder()
							.galContentId(1002145L)
							.galContentTypeId(17L)
							.galTitle("청설모")
							.galWebImageUrl("http://tong.visitkorea.or.kr/cms2/website/44/1002145.jpg")
							.galCreatedtime("20100420024817")
							.galModifiedtime("20150818231341")
							.galPhotographyMonth("201004")
							.galPhotographyLocation("수리산")
							.galPhotographer("직장인동호회 김창윤")
							.galSearchKeyword("청설모, 동물")
							.build(),
						ItemDto.builder()
							.galContentId(1002146L)
							.galContentTypeId(17L)
							.galTitle("청설모")
							.galWebImageUrl("http://tong.visitkorea.or.kr/cms2/website/44/1002146.jpg")
							.galCreatedtime("20100420024817")
							.galModifiedtime("20150818231341")
							.galPhotographyMonth("201004")
							.galPhotographyLocation("관악산")
							.galPhotographer("경기관광공사 김호준")
							.galSearchKeyword("청설모, 동물")
							.build()))
				.totalCount(10)
				.build());

		assertThat(actual.get(1)).isEqualTo(
			PhotoFetchResponseDto.builder()
				.items(
					List.of(
						ItemDto.builder()
							.galContentId(1007757L)
							.galContentTypeId(17L)
							.galTitle("이태원 거리")
							.galWebImageUrl("http://tong.visitkorea.or.kr/cms2/website/44/1007757.jpg")
							.galCreatedtime("20130420123211")
							.galModifiedtime("20191014211530")
							.galPhotographyMonth("201304")
							.galPhotographyLocation("이태원")
							.galPhotographer("김호준")
							.galSearchKeyword("서울, 용산, 이태원")
							.build(),
						ItemDto.builder()
							.galContentId(1007758L)
							.galContentTypeId(17L)
							.galTitle("이태원 거리")
							.galWebImageUrl("http://tong.visitkorea.or.kr/cms2/website/44/1007758.jpg")
							.galCreatedtime("20130420123211")
							.galModifiedtime("20191014211530")
							.galPhotographyMonth("201304")
							.galPhotographyLocation("이태원")
							.galPhotographer("김호준")
							.galSearchKeyword("서울, 용산, 이태원")
							.build(),
						ItemDto.builder()
							.galContentId(1007759L)
							.galContentTypeId(17L)
							.galTitle("이태원 거리")
							.galWebImageUrl("http://tong.visitkorea.or.kr/cms2/website/44/1007759.jpg")
							.galCreatedtime("20130420123211")
							.galModifiedtime("20191014211530")
							.galPhotographyMonth("201304")
							.galPhotographyLocation("이태원")
							.galPhotographer("김호준")
							.galSearchKeyword("서울, 용산, 이태원")
							.build()))
				.totalCount(10)
				.build()
		);
	}
}
