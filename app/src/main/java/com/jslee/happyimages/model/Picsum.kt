package com.jslee.happyimages.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Picsum (
    val id: String,
    val author: String,
    val width: Int,
    val height: Int,
    val url: String,
    val download_url: String)

/**
 * "id":"878",
 * "author":"Richard Loader",
 * "width":2500,
 * "height":1656,
 * "url":"https://unsplash.com/photos/qsEJGX4VqYU",
 * "download_url":"https://picsum.photos/id/878/2500/1656"
 */