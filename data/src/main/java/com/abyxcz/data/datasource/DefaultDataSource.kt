package com.abyxcz.data.datasource

import com.abyxcz.data.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

/**
 * Main entry point for accessing items data.
 */
interface DefaultDataSource {


    fun observeItems(): Flow<Result<List<ItemEntity>>>

    suspend fun getItems(): Result<List<ItemEntity>>

    suspend fun refreshItems()

    fun observeItem(itemId: String): Flow<Result<ItemEntity>>

    suspend fun getItem(itemId: String): Result<ItemEntity>

    suspend fun refreshItem(itemId: String)

    suspend fun saveItem(item: ItemEntity)

    suspend fun saveItems(items: List<ItemEntity>)

    suspend fun updateItem(item: ItemEntity) : Int

    suspend fun updateItems(items: List<ItemEntity>) : Int

    suspend fun clearAllItems()

    suspend fun clearItem(itemId: String)
}