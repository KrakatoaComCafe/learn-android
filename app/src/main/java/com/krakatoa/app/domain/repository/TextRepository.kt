package com.krakatoa.app.domain.repository

import com.krakatoa.app.data.remote.model.TextRequest
import com.krakatoa.app.data.remote.model.TextResponse

interface TextRepository {
    suspend fun getTexts(): List<TextResponse>
    suspend fun sendText(text: TextRequest)
}