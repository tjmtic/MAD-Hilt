package com.abyxcz.mad_hilt.di

import com.abyxcz.mad_hilt.util.CoroutineContextProvider
import com.abyxcz.mad_hilt.util.MainCoroutineContextProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HelperModule {

    @Provides
    fun provideCoroutineContextProvider(): CoroutineContextProvider = MainCoroutineContextProvider()

}