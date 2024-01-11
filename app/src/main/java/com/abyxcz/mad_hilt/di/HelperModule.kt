package com.abyxcz.mad_hilt.di

import android.content.Context
import android.content.SharedPreferences
import com.abyxcz.mad_hilt.R
import com.abyxcz.mad_hilt.util.CoroutineContextProvider
import com.abyxcz.mad_hilt.util.MainCoroutineContextProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object HelperModule {

    @Provides
    fun provideCoroutineContextProvider(): CoroutineContextProvider = MainCoroutineContextProvider()

    @Provides
    @Named("shared")
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    }

}