package ai.viceversa.demo.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import ai.viceversa.demo.OpenApiMock;
import ai.viceversa.demo.api.OpenApiClient;
import ai.viceversa.demo.config.OpenApiFetchProperties;
import ai.viceversa.demo.dto.PhotoSearchRequestDto;
import ai.viceversa.demo.dto.PhotoSearchResponseDto;

@SpringBootTest
@Transactional
class PhotoSearchServiceTest {
	@Autowired
	private PhotoSearchService sut;

	@Autowired
	private PhotoSaveService photoSaveService;

	@TestConfiguration
	static class testConfig {
		@Bean
		OpenApiClient openApiClient() {
			return OpenApiMock.server;
		}
	}

	@BeforeEach
	void setupTestData() {
		int titleFetchCount = 2, detailFetchCount = 3;
		photoSaveService.saveTitleAndDetails(new OpenApiFetchProperties(false, titleFetchCount, detailFetchCount));
	}

	@ParameterizedTest
	@ValueSource(strings = {"청설모", "이태원 거리"})
	@DisplayName("사진 제목으로 검색할 수 있다")
	void test1(String title) {
		PhotoSearchResponseDto actual = sut.findAll(new PhotoSearchRequestDto(title, null, null));
		Assertions.assertThat(actual.items().get(0).title()).isEqualTo(title);
	}

	@ParameterizedTest
	@ValueSource(strings = {"한국관광공사 김지호", "김호준", "직장인동호회 김창윤"})
	@DisplayName("사진 작가로 검색할 수 있다")
	void test2(String photographer) {
		var actual = sut.findAll(new PhotoSearchRequestDto(null, photographer, null));
		Assertions.assertThat(actual.items().get(0).photographer()).isEqualTo(photographer);
	}

	@ParameterizedTest
	@ValueSource(strings = {"청설모"})
	@DisplayName("키워드로 검색할 수 있다")
	void test3(String keyword) {
		var actual = sut.findAll(new PhotoSearchRequestDto(null, null, keyword));
		Assertions.assertThat(actual.items().get(0).searchKeywords().contains(keyword)).isTrue();
	}
}
