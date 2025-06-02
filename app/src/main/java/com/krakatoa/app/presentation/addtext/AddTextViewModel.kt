package com.krakatoa.app.presentation.addtext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krakatoa.app.data.remote.model.TextRequest
import com.krakatoa.app.domain.repository.TextRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddTextViewModel @Inject constructor(
    private val textRepository: TextRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AddTextUiState())
    val uiState: StateFlow<AddTextUiState> = _uiState

    fun onTextChange(newText: String) {
        _uiState.update { it.copy(text = newText) }
    }

    fun sendText() {
        val currentText = _uiState.value.text
        if (currentText.isBlank()) return

        _uiState.update { it.copy(isSending = true, successMessage = null, errorMessage = null) }

        viewModelScope.launch {
            try {
                textRepository.sendText(TextRequest(text = currentText))
                _uiState.value = _uiState.value.copy(
                    isSending = false,
                    successMessage = "Text has been sent with success!",
                    text = ""
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isSending = false,
                    errorMessage = "Error sending text: ${e.message}"
                )
            }
        }
    }
}