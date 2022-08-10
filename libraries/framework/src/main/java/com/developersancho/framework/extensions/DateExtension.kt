package com.developersancho.framework.extensions

import java.text.SimpleDateFormat
import java.util.*

const val ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'"
const val DATE_FORMAT_WITH_DOTS = "dd.MM.yyy"

fun Date.toLongDateString(): String {
    return SimpleDateFormat(
        ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT,
        Locale.getDefault()
    ).format(this)
}

fun String.toReleaseDate(): String {
    return try {
        val dateFormatter = SimpleDateFormat(
            ISO_8601_EXTENDED_DATETIME_TIME_ZONE_FORMAT,
            Locale.getDefault()
        )
        dateFormatter.parse(this)?.let {
            SimpleDateFormat(DATE_FORMAT_WITH_DOTS, Locale.getDefault()).format(it)
        } ?: run { "" }
    } catch (e: Exception) {
        ""
    }
}