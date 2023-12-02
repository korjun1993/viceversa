package ai.viceversa.demo.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ai.viceversa.demo.api.OpenApiClient;

@SpringBootTest
class OpenApiClientTest {

	@Autowired
	private OpenApiClient sut;

	@Test
	@DisplayName("공공 API를 통해 사진 목록을 조회할 수 있다")
	void test1() {
		String actual = sut.getPhotoList(10, 1).blockFirst();
		assert actual != null;
		Assertions.assertThat(actual.contains("\"resultMsg\":\"OK\"")).isTrue();
	}

	@Test
	@DisplayName("공공 API를 통해 제목이 일치하는 사진들을 조회할 수 있다")
	void test2() {
		String actual = sut.getPhotoDetail("이태원거리", 10, 1).blockFirst();
		assert actual != null;
		Assertions.assertThat(actual.contains("\"resultMsg\":\"OK\"")).isTrue();
	}
}
