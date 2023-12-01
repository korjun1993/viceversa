@file:JvmName("ResponseUtils")
package ai.viceversa.demo.extension

import com.fasterxml.jackson.databind.ObjectMapper

fun String.body(): String {
    return ObjectMapper().readTree(this)
        .get("response")
        .get("body")
        .get("items")
        .get("item")
        .toString()
}
