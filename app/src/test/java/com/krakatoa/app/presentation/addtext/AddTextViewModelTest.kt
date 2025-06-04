package com.krakatoa.app.presentation.addtext

import androidx.lifecycle.viewmodel.compose.viewModel
import app.cash.turbine.test
import com.krakatoa.app.domain.usecase.SendTextUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.mock
import kotlin.test.assertEquals


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

    @Test
    fun `addText emits ShowSnackBar and NavigationBack events`() = runTest {
        val fakeUseCase = mock<SendTextUseCase>() {
            onBlocking { invoke("Hello") } doAnswer {}
        }
        val viewModel = AddTextViewModel(fakeUseCase)

        viewModel.uiEvent.test {
            viewModel.addText("Hello")

            val snackBarEvent = awaitItem()
            val navigationEvent = awaitItem()

            assertTrue(snackBarEvent is AddTextUiEvent.ShowSnackBar)
            assertEquals("Text added successfully!", (snackBarEvent as AddTextUiEvent.ShowSnackBar).message)

            assertEquals(AddTextUiEvent.NavigationBack, navigationEvent)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `addTest emits ShowSnackBar on error`() = runTest {
        val fakeUseCase = mock<SendTextUseCase>() {
            onBlocking { invoke("Hello") } doAnswer {
                throw RuntimeException("Network error")
            }
        }

        val viewModel = AddTextViewModel(fakeUseCase)

        viewModel.uiEvent.test {
            viewModel.addText("Hello")

            val errorEvent = awaitItem()
            assertTrue(errorEvent is AddTextUiEvent.ShowSnackBar)
            assertTrue((errorEvent as AddTextUiEvent.ShowSnackBar).message.contains("Network error"))

            cancelAndIgnoreRemainingEvents()
        }
    }
}