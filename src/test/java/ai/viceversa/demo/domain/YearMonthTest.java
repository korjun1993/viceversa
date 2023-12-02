package ai.viceversa.demo.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class YearMonthTest {
	@Test
	@DisplayName("연도/월을 표현하는 문자열을 YearMonth 로 변경한다")
	void test1() {
		YearMonth actual = YearMonth.of("201311");
		Assertions.assertThat(actual).isEqualTo(new YearMonth(2013, 11));
	}

	@Test
	@DisplayName("연도/월을 표현하는 문자열 중 누락된 것이 있다면 null을 반환한다")
	void test2() {
		YearMonth actual = YearMonth.of("2013");
		Assertions.assertThat(actual).isNull();
	}

	@Test
	@DisplayName("연도/월을 표현하는 문자열에 올바르지 않은 값이 포함된다면 null을 반환한다")
	void test3() {
		YearMonth actual = YearMonth.of("201377");
		Assertions.assertThat(actual).isNull();
	}
}
