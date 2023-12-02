package ai.viceversa.demo.service;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import ai.viceversa.demo.domain.Photo;
import ai.viceversa.demo.repository.PhotoRepository;

@Transactional
@SpringBootTest
class PhotoSaveServiceTest {

	@Autowired
	private PhotoSaveService sut;

	@Autowired
	private PhotoRepository photoRepository;

	@Test
	@DisplayName("오픈 API를 통해 numOfRows 개수만큼 사진 목록 정보를 조회하고 데이터베이스에 저장한다")
	void test1() {
		sut.saveList(10, 1);
		List<Photo> actual = photoRepository.findAll();
		Assertions.assertThat(actual.size()).isEqualTo(10);
	}

	@Test
	@DisplayName("오픈 API를 통해 제목으로 사진 상세 정보를 numOfRows 개수만큼 조회하고 데이터베이스에 저장한다")
	void test2() {
		sut.saveDetail("이태원거리", 10, 1);
		List<Photo> actual = photoRepository.findAll();
		Assertions.assertThat(actual.size()).isEqualTo(10);
	}
}
