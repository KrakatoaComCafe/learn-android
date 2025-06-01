package com.krakatoa.app.presentation.addtext

data class AddTextUiState(
    val text: String = "",
    val isSending: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)
