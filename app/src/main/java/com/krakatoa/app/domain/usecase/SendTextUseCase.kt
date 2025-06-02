package com.krakatoa.app.domain.usecase

import com.krakatoa.app.data.remote.model.TextRequest
import com.krakatoa.app.domain.repository.TextRepository
import javax.inject.Inject

class SendTextUseCase @Inject constructor(
    private val textRepository: TextRepository
) {
    suspend operator fun invoke(text: String) {
        val textRequest = TextRequest(text)
        return textRepository.sendText(textRequest)
    }
}