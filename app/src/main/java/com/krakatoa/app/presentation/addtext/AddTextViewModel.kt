package com.krakatoa.app.presentation.addtext

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddTextViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(AddTextUiState())
    val uiState: StateFlow<AddTextUiState> = _uiState

    fun onTextChange(newText: String) {
        _uiState.update { it.copy(text = newText) }
    }

    fun sendText() {
        _uiState.update { it.copy(isSending = true, successMessage = null, errorMessage = null) }

        kotlinx.coroutines.GlobalScope.launch {
            kotlinx.coroutines.delay(1000)
            _uiState.update {
                it.copy(
                    isSending = false,
                    successMessage = "Text has been sent with success!",
                    text = ""
                )
            }
        }
    }
}