@file:JvmName("ParsingUtils")

package ai.viceversa.demo.extension

import ai.viceversa.demo.dto.PhotoFetchResponseDto
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory

private val log = LoggerFactory.getLogger(object {}::class.java.`package`.name)!!

fun String.body(): String? {
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

fun ObjectMapper.readValue(data: String?): List<PhotoFetchResponseDto> {
    return if (data.isNullOrBlank()) {
        emptyList()
    } else try {
        readValue<List<PhotoFetchResponseDto>>(
            data.body(),
            typeFactory.constructCollectionType(
                MutableList::class.java,
                PhotoFetchResponseDto::class.java
            )
        )
    } catch (e: JsonProcessingException) {
        log.error("Open API 데이터(JSON) → 파싱 오류 발생 = {}", data, e)
        throw RuntimeException(e)
    }
}
