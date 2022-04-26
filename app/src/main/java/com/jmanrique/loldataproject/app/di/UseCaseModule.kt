package com.jmanrique.loldataproject.app.di

import com.jmanrique.loldataproject.domain.usecases.champions.GetChampionDetailUseCase
import com.jmanrique.loldataproject.domain.usecases.champions.GetChampionSummaryUseCase
import com.jmanrique.loldataproject.domain.usecases.champions.impl.GetChampionDetailUseCaseImpl
import com.jmanrique.loldataproject.domain.usecases.champions.impl.GetChampionSummaryUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetChampionSummaryUseCase(impl: GetChampionSummaryUseCaseImpl): GetChampionSummaryUseCase = impl

    @Singleton
    @Provides
    fun providesGetChampionDetailUseCase(impl: GetChampionDetailUseCaseImpl): GetChampionDetailUseCase = impl
}