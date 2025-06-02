package com.krakatoa.app.presentation.listtext

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krakatoa.app.domain.usecase.GetTextUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTextViewModel @Inject constructor(
    private val getTextUseCase: GetTextUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<ListTextUiState>(ListTextUiState.Idle)
    val uiState: StateFlow<ListTextUiState> = _uiState

    fun loadTexts() {
        _uiState.value = ListTextUiState.Loading

        viewModelScope.launch {
            try {
                val texts = getTextUseCase()
                _uiState.value = ListTextUiState.Success(texts)
            } catch (e: Exception) {
                _uiState.value = ListTextUiState.Error("Error loading texts: [${e.message}]")
            }
        }
    }
}