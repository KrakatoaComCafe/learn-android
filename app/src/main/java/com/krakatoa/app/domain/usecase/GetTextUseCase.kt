package com.krakatoa.app.domain.usecase

import com.krakatoa.app.data.remote.model.TextResponse
import com.krakatoa.app.domain.repository.TextRepository
import javax.inject.Inject

class GetTextUseCase @Inject constructor(
    private val textRepository: TextRepository
) {
    suspend operator fun invoke(): List<TextResponse> {
        return textRepository.getTexts()
    }
}