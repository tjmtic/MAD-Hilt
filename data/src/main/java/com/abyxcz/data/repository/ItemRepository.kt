package com.abyxcz.data.repository

import com.abyxcz.data.model.Item
import com.abyxcz.data.datasource.Result
import kotlinx.coroutines.flow.Flow


/**
 * Interface to the data layer.
 */
interface ItemRepository {

    fun observeItems(): Flow<Result<List<Item>>>

    suspend fun getItems(forceUpdate: Boolean = false): Result<List<Item>>

    suspend fun refreshItems()

    fun observeItem(itemId: String): Flow<Result<Item>>

    suspend fun getItem(itemId: String, forceUpdate: Boolean = false): Result<Item>

    suspend fun refreshItem(itemId: String)

    suspend fun saveItem(item: Item)

    suspend fun completeItem(item: Item)

    suspend fun completeItem(itemId: String)

    suspend fun activateItem(item: Item)

    suspend fun activateItem(itemId: String)

    suspend fun clearCompletedItems()

    suspend fun deleteAllItems()

    suspend fun deleteItem(itemId: String)

    suspend fun syncData()
}


