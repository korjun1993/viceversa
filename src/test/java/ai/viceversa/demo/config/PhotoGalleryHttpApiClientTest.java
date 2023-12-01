package ai.viceversa.demo.config;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ai.viceversa.demo.api.PhotoGalleryHttpApiClient;

@SpringBootTest
class PhotoGalleryHttpApiClientTest {

	@Autowired
	private PhotoGalleryHttpApiClient sut;

	@Test
	@DisplayName("")
	void test1() {
		String actual = sut.getPhotoList(10, 1);
		Assertions.assertThat(actual.contains("\"resultMsg\":\"OK\"")).isTrue();
	}
}
