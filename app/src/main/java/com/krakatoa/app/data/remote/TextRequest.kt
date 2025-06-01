package com.krakatoa.app.data.remote

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TextRequest(
    val text: String
)
