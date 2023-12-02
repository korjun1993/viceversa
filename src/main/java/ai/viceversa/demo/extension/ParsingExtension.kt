@file:JvmName("ParsingUtils")

package ai.viceversa.demo.extension

import ai.viceversa.demo.dto.ItemDto
import ai.viceversa.demo.dto.PhotoFetchResponseDto
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger(object {}::class.java.`package`.name)!!

fun String.items(): String? {
    return try {
        ObjectMapper().readTree(this)
            .get("response")
            .get("body")
            .get("items")
            .get("item")
            .toString()
    } catch (e: NullPointerException) {
        null
    }
}

fun String.totalCount(): String? {
    return try {
        ObjectMapper().readTree(this)
            .get("response")
            .get("body")
            .get("totalCount")
            .toString()
    } catch (e: NullPointerException) {
        null
    }
}

fun ObjectMapper.readValue(data: String?): PhotoFetchResponseDto? {
    return if (data.isNullOrBlank()) {
        null
    } else try {
        val items = readValue<List<ItemDto>>(
            data.items(),
            typeFactory.constructCollectionType(MutableList::class.java, ItemDto::class.java)
        )
        val totalCount = data.totalCount()?.toIntOrNull() ?: 0
        PhotoFetchResponseDto(items, totalCount)
    } catch (e: Exception) {
        log.debug("Open API 데이터(JSON) → 파싱 오류 발생 = {}", data, e)
        null
    }
}
