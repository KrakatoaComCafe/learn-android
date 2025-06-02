package com.krakatoa.app.data.remote

import com.krakatoa.app.data.remote.model.TextRequest
import com.krakatoa.app.data.remote.model.TextResponse
import com.krakatoa.app.domain.repository.TextRepository
import javax.inject.Inject

class TextRepositoryImpl @Inject constructor(
    private val textApi: TextApi
): TextRepository {
    override suspend fun getTexts(): List<TextResponse> {
        return textApi.getTexts()
    }

    override suspend fun sendText(text: TextRequest) {
        return textApi.sendText(text)
    }
}
