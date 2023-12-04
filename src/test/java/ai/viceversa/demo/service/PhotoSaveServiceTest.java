package ai.viceversa.demo.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import ai.viceversa.demo.OpenApiMock;
import ai.viceversa.demo.api.OpenApiClient;
import ai.viceversa.demo.config.OpenApiFetchProperties;
import ai.viceversa.demo.repository.PhotoRepository;

@Transactional
@SpringBootTest
class PhotoSaveServiceTest {

	@Autowired
	private PhotoSaveService sut;

	@Autowired
	private PhotoRepository photoRepository;

	@TestConfiguration
	static class testConfig {
		@Bean
		OpenApiClient openApiClient() {
			return OpenApiMock.server;
		}
	}

	@Test
	@DisplayName("오픈 API를 통해 2개의 사진 제목을 조회하고, 제목 당 3개의 상세 정보를 데이터베이스에 저장한다")
	void test1() {
		// given
		int titleFetchCount = 2, detailFetchCount = 3;

		// when
		sut.saveTitleAndDetails(new OpenApiFetchProperties(false, titleFetchCount, detailFetchCount));

		// then
		Assertions.assertThat(photoRepository.findByTitle_Id("청설모").size()).isEqualTo(3);
		Assertions.assertThat(photoRepository.findByTitle_Id("이태원 거리").size()).isEqualTo(3);
	}
}
