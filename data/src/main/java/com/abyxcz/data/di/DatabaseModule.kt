package com.abyxcz.data.di

import android.app.Application
import androidx.room.Room
import com.abyxcz.data.db.ItemDB
import com.abyxcz.data.db.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideDatabase(app: Application): ItemDB =
        Room.databaseBuilder(app, ItemDB::class.java, "item_db").fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideUserDao(itemDB: ItemDB): ItemDao = itemDB.ItemDao()
}