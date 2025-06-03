package com.krakatoa.app.domain.usecase

import com.krakatoa.app.data.remote.model.TextRequest
import com.krakatoa.app.domain.repository.TextRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class SendTextUseCaseTest {
    private lateinit var repository: TextRepository
    private lateinit var useCase: SendTextUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = SendTextUseCase(repository)
    }

    @Test
    fun `should call sendText on repository`() = runTest {
        val text = "hello"
        val textRequest = TextRequest(text)
        useCase(text)
        verify(repository).sendText(textRequest)
    }
}