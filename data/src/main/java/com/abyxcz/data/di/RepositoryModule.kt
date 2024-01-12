package com.abyxcz.data.di

import com.abyxcz.data.datasource.DefaultDataSource
import com.abyxcz.data.repository.ItemRepository
import com.abyxcz.data.repository.ItemRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideItemRepository(
        @Named("local") itemLocalDataSource: DefaultDataSource
    ): ItemRepository =
        ItemRepositoryImpl(itemsLocalDataSource = itemLocalDataSource)

}