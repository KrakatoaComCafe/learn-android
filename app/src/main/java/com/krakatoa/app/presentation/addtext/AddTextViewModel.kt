package com.krakatoa.app.presentation.addtext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krakatoa.app.data.remote.model.TextRequest
import com.krakatoa.app.domain.usecase.SendTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddTextViewModel @Inject constructor(
    private val sendTextUseCase: SendTextUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<AddTextUiState>(AddTextUiState.Idle)
    val uiState: StateFlow<AddTextUiState> = _uiState

    fun addText(text: String) {
        _uiState.value = AddTextUiState.Loading
        viewModelScope.launch {
            try {
                sendTextUseCase(text)
                _uiState.value = AddTextUiState.Success(text)
            } catch (e: Exception) {
                _uiState.value = AddTextUiState.Error("Error to send text: [${e.message}]")
            }
        }
    }
}