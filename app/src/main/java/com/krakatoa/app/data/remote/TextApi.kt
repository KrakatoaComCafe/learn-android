package com.krakatoa.app.data.remote

import com.krakatoa.app.data.remote.model.TextRequest
import com.krakatoa.app.data.remote.model.TextResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TextApi {
    @POST("/messages")
    suspend fun sendText(@Body request: TextRequest)
    @GET("/messages")
    suspend fun getTexts(): List<TextResponse>
}