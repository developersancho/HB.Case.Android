package com.developersancho.hb.features.search

enum class SearchFilterType(val type: String) {
    MOVIE("movie"),
    MUSIC("song"),
    EBOOK("ebook"),
    PODCAST("podcast");

    companion object {
        fun from(findType: String?): SearchFilterType? =
            values().firstOrNull { it.type == findType }
    }
}