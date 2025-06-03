package com.krakatoa.app.presentation.addtext

sealed class AddTextUiEvent {
    data class ShowSnackBar(val message: String) : AddTextUiEvent()
    object NavigationBack: AddTextUiEvent()
}