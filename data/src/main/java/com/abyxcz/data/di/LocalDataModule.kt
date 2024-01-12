package com.abyxcz.data.di

import com.abyxcz.data.datasource.DefaultDataSource
import com.abyxcz.data.datasource.ItemLocalDataSource
import com.abyxcz.data.db.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    @Named("local")
    fun provideLocalDataSource(itemDao: ItemDao): DefaultDataSource =
        ItemLocalDataSource(itemDao = itemDao)
}