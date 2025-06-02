package com.krakatoa.app.presentation.listtext

import com.krakatoa.app.data.remote.model.TextResponse

sealed class ListTextUiState {
    object Loading : ListTextUiState()
    data class Success(val texts: List<TextResponse>) : ListTextUiState()
    data class Error(val message: String) : ListTextUiState()
    object Idle : ListTextUiState()
}
