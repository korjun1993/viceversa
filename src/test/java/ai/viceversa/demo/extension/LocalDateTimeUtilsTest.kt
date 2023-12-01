package ai.viceversa.demo.extension

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.format.DateTimeParseException

class LocalDateTimeUtilsTest {
    @Test
    @DisplayName("연도/월/날짜/시간를 표현하는 문자열을 LocalDateTime 으로 변경한다")
    fun test1() {
        val actual = "20231123110634".toLocalDateTime()
        Assertions.assertThat(actual).isEqualTo(LocalDateTime.of(2023, 11, 23, 11, 6, 34))
    }

    @Test
    @DisplayName("문자열에 연도/월/날짜/시간 정보 중 누락된 것이 있다면 예외가 발생한다")
    fun test2() {
        Assertions.assertThatThrownBy { "202311".toLocalDateTime() }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    @DisplayName("연도/월/날짜/시간에 올바르지 않은 값이 포함된다면 예외가 발생한다")
    fun test3() {
        Assertions.assertThatThrownBy { "20231323110634".toLocalDateTime() }
            .isInstanceOf(DateTimeParseException::class.java)
    }
}
