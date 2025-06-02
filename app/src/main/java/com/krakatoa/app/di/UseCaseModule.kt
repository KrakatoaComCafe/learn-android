package com.krakatoa.app.di

import com.krakatoa.app.domain.repository.TextRepository
import com.krakatoa.app.domain.usecase.GetTextUseCase
import com.krakatoa.app.domain.usecase.SendTextUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetTextUseCase(textRepository: TextRepository): GetTextUseCase {
        return GetTextUseCase(textRepository)
    }

    @Provides
    @Singleton
    fun provideSendTextUseCase(textRepository: TextRepository): SendTextUseCase {
        return SendTextUseCase(textRepository)
    }
}