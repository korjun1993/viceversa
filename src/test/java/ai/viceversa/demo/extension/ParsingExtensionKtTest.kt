package ai.viceversa.demo.extension

import ai.viceversa.demo.dto.PhotoFetchResponseDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class ParsingExtensionKtTest {
    @Test
    @DisplayName("공공 API 호출 결과를 파싱하여 PhotoFetchResponseDto 객체로 파싱할 수 있다")
    fun test1() {
        val data =
            """
            {
                "response": {
                    "header": {"resultCode":"0000","resultMsg":"OK"},
                    "body": {
                        "items": {
                            "item":[
                                {
                                    "galContentId":"1002144",
                                    "galContentTypeId":"17",
                                    "galTitle":"청설모",
                                    "galWebImageUrl":"http://tong.visitkorea.or.kr/cms2/website/44/1002144.jpg",
                                    "galCreatedtime":"20100420024817",
                                    "galModifiedtime":"20150818231341",
                                    "galPhotographyMonth":"201004",
                                    "galPhotographyLocation":"서울 경복궁",
                                    "galPhotographer":"한국관광공사 김지호",
                                    "galSearchKeyword":"청설모, 동물"
                                }
                            ]
                        },
                        "numOfRows":10,
                        "pageNo":1,
                        "totalCount":5455
                    }
                }
            }
            """.trimIndent()

        val actual = ObjectMapper().readValue(data)

        Assertions.assertThat(actual).isEqualTo(
            listOf(
                PhotoFetchResponseDto.builder()
                    .galContentId(1002144)
                    .galContentTypeId(17)
                    .galTitle("청설모")
                    .galWebImageUrl("http://tong.visitkorea.or.kr/cms2/website/44/1002144.jpg")
                    .galCreatedtime("20100420024817")
                    .galModifiedtime("20150818231341")
                    .galPhotographyMonth("201004")
                    .galPhotographyLocation("서울 경복궁")
                    .galPhotographer("한국관광공사 김지호")
                    .galSearchKeyword("청설모, 동물")
                    .build()
            )
        )
    }

    @Test
    @DisplayName("null 데이터를 파싱하면 빈 리스트를 반환한다")
    fun test2() {
        val actual = ObjectMapper().readValue(null)
        Assertions.assertThat(actual).isEqualTo(emptyList<PhotoFetchResponseDto>())
    }

    @Test
    @DisplayName("공공 API 호출 결과, Item이 존재하지 않는다면 빈 리스트를 반환한다")
    fun test3() {
        val data =
            """
            {
                "response": {
                    "header": {"resultCode":"0000","resultMsg":"OK"},
                    "body": {
                        "items": "",
                        "numOfRows":10,
                        "pageNo":1,
                        "totalCount":5455
                    }
                }
            }
            """.trimIndent()

        val actual = ObjectMapper().readValue(data)
        Assertions.assertThat(actual).isEqualTo(emptyList<PhotoFetchResponseDto>())
    }
}
