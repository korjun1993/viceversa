package ai.viceversa.demo.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import ai.viceversa.demo.dto.PhotoSearchRequestDto;

@Tag("integration")
@Transactional
@SpringBootTest
class PhotoControllerTest {

	@Autowired
	private PhotoController sut;

	@Autowired
	private ObjectMapper objectMapper;

	private MockMvc mock;

	@BeforeEach
	void setUp() {
		mock = MockMvcBuilders.standaloneSetup(sut)
			.build();
	}

	@Test
	@DisplayName("단일 조건으로 사진 정보를 조회한다")
	void test1() throws Exception {
		PhotoSearchRequestDto request = PhotoSearchRequestDto.builder()
			.photographer("한국관광공사 김지호")
			.build();

		mock.perform(MockMvcRequestBuilders.get("/photos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.print());
	}

	@Test
	@DisplayName("복수 조건으로 사진 정보를 조회한다")
	void test2() throws Exception {
		PhotoSearchRequestDto request = PhotoSearchRequestDto.builder()
			.photographer("한국관광공사 김지호")
			.keyword("동물")
			.photoTitle("청설모")
			.build();

		mock.perform(MockMvcRequestBuilders.get("/photos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.print());
	}
}
