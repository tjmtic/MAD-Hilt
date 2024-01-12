package com.abyxcz.data.repository

import com.abyxcz.data.datasource.ItemLocalDataSource
import com.abyxcz.data.model.Item
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.Flow

import kotlinx.coroutines.flow.mapLatest
import com.abyxcz.data.datasource.Result
import com.abyxcz.data.entity.ItemEntity
import com.abyxcz.data.util.Converters


/**
 * Concrete implementation to load items from the data sources into a cache.
 */
class ItemsRepositoryImpl(
    //private val itemsRemoteDataSource: ItemsRemoteDataSource,
    private val itemsLocalDataSource: ItemLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO) : ItemsRepository {

    override suspend fun getItems(forceUpdate: Boolean): Result<List<Item>> {
        if (forceUpdate) {
            try {
               // updateItemsFromRemoteDataSource()
            } catch (ex: Exception) {
                return Result.Error(ex)
            }
        }

        return when(val resp = itemsLocalDataSource.getItems()) {
            is Result.Loading -> Result.Loading
            is Result.Error -> Result.Error(resp.exception)
            is Result.Success -> {
                Result.Success(resp.data.map{
                    Converters().itemFromItemEntity(it)
                })
            }
        }
    }

    override suspend fun refreshItems() {
      //  updateItemsFromRemoteDataSource()
    }

    override fun observeItems(): Flow<Result<List<Item>>> {
        return itemsLocalDataSource.observeItems().mapLatest {
            when (it) {
                is Result.Loading -> Result.Loading
                is Result.Error -> Result.Error(it.exception)
                is Result.Success -> {
                    Result.Success(it.data.map { itemEntity ->
                        Converters().itemFromItemEntity(itemEntity)
                    })
                }
            }
        }
    }

    override suspend fun refreshItem(itemId: String) {
       // updateItemFromRemoteDataSource(itemId)
    }

    /*private suspend fun updateItemsFromRemoteDataSource() {
        val remoteItems = itemsRemoteDataSource.getItems()

        if (remoteItems is Result.Success) {
            // Real apps might want to do a proper sync.
            itemsLocalDataSource.deleteAllItems()
            remoteItems.data.forEach { item ->
                itemsLocalDataSource.saveItem(item)
            }
        } else if (remoteItems is Result.Error) {
            throw remoteItems.exception
        }
    }*/

    override fun observeItem(itemId: String): Flow<Result<Item>> {
        return itemsLocalDataSource.observeItem(itemId).mapLatest {
            when (it) {
                is Result.Loading -> Result.Loading
                is Result.Error -> Result.Error(it.exception)
                is Result.Success -> {
                    Result.Success(
                        Converters().itemFromItemEntity(it.data)
                    )
                }
            }
        }
    }

    /*private suspend fun updateItemFromRemoteDataSource(itemId: String) {
        val remoteItem = itemsRemoteDataSource.getItem(itemId)

        if (remoteItem is Result.Success) {
            itemsLocalDataSource.saveItem(remoteItem.data)
        }
    }*/

    /**
     * Relies on [getItems] to fetch data and picks the item with the same ID.
     */
    override suspend fun getItem(itemId: String, forceUpdate: Boolean): Result<Item> {
        //if (forceUpdate) {
        //    updateItemFromRemoteDataSource(itemId)
        //}
        //return itemsLocalDataSource.getItem(itemId)

        return when(val resp = itemsLocalDataSource.getItem(itemId)) {
            is Result.Loading -> Result.Loading
            is Result.Error -> Result.Error(resp.exception)
            is Result.Success -> {
                Result.Success(Converters().itemFromItemEntity(resp.data))
            }
        }
    }

    override suspend fun saveItem(item: Item) {
        coroutineScope {
            //launch { itemsRemoteDataSource.saveItem(Converters.itemEntityFromItem(item)) }
            //launch { itemsLocalDataSource.saveItem(Converters().itemEntityFromItem(item)) }
        }
    }

    override suspend fun completeItem(item: Item) {
        coroutineScope {
            //launch { itemsRemoteDataSource.completeItem(Converters.itemEntityFromItem(item)) }
            //launch { itemsLocalDataSource.completeItem(Converters.itemEntityFromItem(item)) }
        }
    }

    override suspend fun completeItem(itemId: String) {
        withContext(ioDispatcher) {
            (getItemWithId(itemId) as? Result.Success)?.let { it ->
                completeItem(it.data)
            }
        }
    }

    override suspend fun activateItem(item: Item) = withContext<Unit>(ioDispatcher) {
        coroutineScope {
            //launch { itemsRemoteDataSource.activateItem(Converters.itemEntityFromItem(item)) }
            //launch { itemsLocalDataSource.activateItem(Converters.itemEntityFromItem(item)) }
        }
    }

    override suspend fun activateItem(itemId: String) {
        withContext(ioDispatcher) {
            (getItemWithId(itemId) as? Result.Success)?.let { it ->
                activateItem(it.data)
            }
        }
    }

    override suspend fun clearCompletedItems() {
        coroutineScope {
            //launch { itemsRemoteDataSource.clearCompletedItems() }
            //launch { itemsLocalDataSource.clearCompletedItems() }
        }
    }

    override suspend fun deleteAllItems() {
        withContext(ioDispatcher) {
            coroutineScope {
               // launch { itemsRemoteDataSource.deleteAllItems() }
               // launch { itemsLocalDataSource.deleteAllItems() }
            }
        }
    }

    override suspend fun deleteItem(itemId: String) {
        coroutineScope {
           // launch { itemsRemoteDataSource.deleteItem(itemId) }
            //launch { itemsLocalDataSource.deleteItem(itemId) }
        }
    }

    override suspend fun syncData() { /*
        // Fetch data from the remote source
        val remoteItems  = when(val remoteItems1 = itemsRemoteDataSource.getItems()){
            is Result.Success -> remoteItems1.data
            else -> { emptyList() }
        }

        // Convert remote data to local entities
        val localItems = remoteItems.map { ItemEntity(it.id, it.title) }

        //TODO: COMBINE this FILTER call to a single transaction
        // Compare remote and local data
        val newItems = localItems.filter { localItem ->
            when(val item = itemsLocalDataSource.getItem(localItem.id)){
                is Result.Success -> item.data.id === localItem.id
                else -> { false }
            }
        }

        val localUpdates = localItems.filterNot { localItem ->
            when(val item = itemsLocalDataSource.getItem(localItem.id)){
                is Result.Success -> item.data.id === localItem.id
                else -> { false }
            }
        }

        // Update the local database with new or updated items
        //Update First...
        itemsLocalDataSource.updateItems(localUpdates)
        //...Then Save New
        itemsLocalDataSource.saveItems(newItems)

        // Handle deleted items if needed
        handleDeletedItems(remoteItems, localItems)
    */ }

    private suspend fun handleDeletedItems(remoteItems: List<ItemEntity>, localItems: List<ItemEntity>) {
        val remoteItemIds = remoteItems.map { it.id }
        val localItemIds = localItems.map { it.id }

        val deletedItemIds = localItemIds.filterNot { remoteItemIds.contains(it) }

        coroutineScope {
            launch {
                // Mark items as deleted in the local database
                //itemsLocalDataSource.deleteItems(deletedItemIds)
            }
        }
    }

    private suspend fun getItemWithId(id: String): Result<Item> {

        return when(val resp = itemsLocalDataSource.getItem(id)) {
            is Result.Loading -> resp
            is Result.Error -> resp
            is Result.Success -> {
                Result.Success(Converters().itemFromItemEntity(resp.data))
            }
        }
    }
}