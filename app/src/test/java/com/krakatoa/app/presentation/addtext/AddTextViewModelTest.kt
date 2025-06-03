package com.krakatoa.app.presentation.addtext

import app.cash.turbine.test
import com.krakatoa.app.domain.usecase.SendTextUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock


class AddTextViewModelTest {
    @Test
    fun `addText emits Loading then Success`() = runTest {
        val fakeUseCase = mock<SendTextUseCase> {
            onBlocking { invoke("Hello") } doAnswer {}
        }

        val viewModel = AddTextViewModel(fakeUseCase)

        viewModel.uiState.test {
            viewModel.addText("Hello")

            val idle = awaitItem()
            val loading = awaitItem()
            val success = awaitItem()

            assertTrue(idle is AddTextUiState.Idle)
            assertTrue(loading is AddTextUiState.Loading)
            assertTrue(success is AddTextUiState.Success)
        }
    }
}