package com.krakatoa.app.presentation.addtext

sealed class AddTextUiState {
    object Idle: AddTextUiState()
    object Loading: AddTextUiState()
    data class Success(val text: String): AddTextUiState()
    data class Error(val message: String): AddTextUiState()
}
