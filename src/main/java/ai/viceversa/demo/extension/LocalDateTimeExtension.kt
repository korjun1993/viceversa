@file:JvmName("LocalDateTimeUtils")

package ai.viceversa.demo.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private const val DEFAULT_SIZE = 14

fun String.toLocalDateTime(): LocalDateTime {
    require(length == DEFAULT_SIZE) { "날짜 문자열의 길이는" + DEFAULT_SIZE + "이어야 합니다" }
    return LocalDateTime.parse(this, DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
}
