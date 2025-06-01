package com.krakatoa.app.presentation.listtext

import com.krakatoa.app.data.remote.TextResponse

data class ListTextUiState(
    val isLoading: Boolean = false,
    val texts: List<TextResponse> = emptyList(),
    val errorMessage: String? = null
)
