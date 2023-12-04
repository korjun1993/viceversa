package ai.viceversa.demo.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class YearMonth {
	private static final int YEAR_MIN_VALUE = 0;
	private static final int MONTH_MIN_VALUE = 1;
	private static final int MONTH_MAX_VALUE = 12;

	private Integer year;

	private Integer month;

	public YearMonth(int year, int month) {
		validate(year, month);
		this.year = year;
		this.month = month;
	}

	/**
	 * @param time year + month (ex) 201311
	 * @return YearMonth
	 */
	public static YearMonth of(String time) {
		try {
			int year = Integer.parseInt(time.substring(0, 4));
			int month = Integer.parseInt(time.substring(4, 6));
			return new YearMonth(year, month);
		} catch (Exception e) {
			return null;
		}
	}

	public static YearMonth of(int year, int month) {
		return new YearMonth(year, month);
	}

	private void validate(int year, int month) {
		if (year < YEAR_MIN_VALUE || month < MONTH_MIN_VALUE || month > MONTH_MAX_VALUE) {
			throw new IllegalArgumentException("연도(" + year + "), 월(" + month + ") 의 값에 올바른 값을 입력하세요");
		}
	}

	@Override
	public String toString() {
		return "" + year + month;
	}
}
