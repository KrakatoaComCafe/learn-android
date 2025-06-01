package com.krakatoa.app.presentation.listtext

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krakatoa.app.data.remote.TextApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ListTextViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(ListTextUiState())
    val uiState: StateFlow<ListTextUiState> = _uiState

    fun loadTexts() {
        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            try {
                val texts = TextApiService.api.getTexts()
                Log.d("API", "GET returned: $texts")
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    texts = texts
                )
            } catch (e: Exception) {
                Log.d("API", "Failed ${e.message}")
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = "Error to search for texts: ${e.message}"
                )
            }
        }
    }
}