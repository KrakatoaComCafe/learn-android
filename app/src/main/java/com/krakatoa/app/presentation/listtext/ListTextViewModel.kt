package com.krakatoa.app.presentation.listtext

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krakatoa.app.domain.repository.TextRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTextViewModel @Inject constructor(
    private val textRepository: TextRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(ListTextUiState())
    val uiState: StateFlow<ListTextUiState> = _uiState

    fun loadTexts() {
        _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            try {
                val texts = textRepository.getTexts()
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