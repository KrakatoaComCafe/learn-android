package com.krakatoa.app.presentation.addtext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krakatoa.app.domain.usecase.SendTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddTextViewModel @Inject constructor(
    private val sendTextUseCase: SendTextUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<AddTextUiState>(AddTextUiState.Idle)
    val uiState: StateFlow<AddTextUiState> = _uiState
    private val _uiEvent = Channel<AddTextUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun addText(text: String) {
        viewModelScope.launch {
        _uiState.value = AddTextUiState.Loading
            try {
                sendTextUseCase(text)
                _uiState.value = AddTextUiState.Success(text)
                _uiEvent.send(AddTextUiEvent.ShowSnackBar("Text added successfully!"))
                _uiEvent.send(AddTextUiEvent.NavigationBack)
            } catch (e: Exception) {
                _uiEvent.send(AddTextUiEvent.ShowSnackBar("Error sending message: [${e.message}]"))
            }
        }
    }
}