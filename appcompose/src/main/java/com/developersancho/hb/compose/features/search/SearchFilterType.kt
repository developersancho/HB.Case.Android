package com.developersancho.hb.compose.features.search

import android.content.Context
import com.developersancho.hb.compose.R

enum class SearchFilterType(val type: String) {
    MOVIE("movie"),
    MUSIC("song"),
    EBOOK("ebook"),
    PODCAST("podcast");

    companion object {
        fun from(findType: String?): SearchFilterType? =
            values().firstOrNull { it.type == findType }

        fun fromName(context: Context, findName: String?): SearchFilterType? {
            return when (findName) {
                context.getString(R.string.chip_movie) -> MOVIE
                context.getString(R.string.chip_music) -> MUSIC
                context.getString(R.string.chip_ebook) -> EBOOK
                context.getString(R.string.chip_podcast) -> PODCAST
                else -> null
            }
        }
    }
}