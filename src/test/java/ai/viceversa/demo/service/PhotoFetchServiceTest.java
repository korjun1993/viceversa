package ai.viceversa.demo.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PhotoFetchServiceTest {

	@Autowired
	private PhotoFetchService sut;

	@Test
	@DisplayName("오픈 API를 통해 10개의 사진 정보를 조회한다")
	void test1() {
		var actual = sut.fetchList(10, 1);
		System.out.println(actual);
		Assertions.assertThat(actual.size()).isEqualTo(10);
	}

	@Test
	@DisplayName("오픈 API를 통해 제목으로 10개의 사진 정보를 조회한다")
	void test2() {
		var actual = sut.fetchDetail("이태원거리", 10, 1);
		Assertions.assertThat(actual.size()).isEqualTo(10);
	}
}
