package com.abyxcz.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abyxcz.data.entity.ItemEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the items table.
 */
@Dao
interface ItemDao {

        /**
         * Observes list of items.
         *
         * @return all items.
         */
        @Query("SELECT * FROM Items")
        fun observeItems(): Flow<List<ItemEntity>>

        /**
         * Observes a single item.
         *
         * @param itemId the item id.
         * @return the item with itemId.
         */
        @Query("SELECT * FROM Items WHERE id = :itemId")
        fun observeItemById(itemId: String): Flow<ItemEntity>

        /**
         * Select all items from the items table.
         *
         * @return all items.
         */
        @Query("SELECT * FROM Items")
        suspend fun getItems(): List<ItemEntity>

        /**
         * Select a item by id.
         *
         * @param itemId the item id.
         * @return the item with itemId.
         */
        @Query("SELECT * FROM Items WHERE id = :itemId")
        suspend fun getItemById(itemId: String): ItemEntity?

        /**
         * Insert a item in the database. If the item already exists, replace it.
         *
         * @param itemEntity the item to be inserted.
         */
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertItem(itemEntity: ItemEntity)

        /**
         * Insert a list of items in the database. If the item already exists, replace it.
         *
         * @param itemEntity the item to be inserted.
         */
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertItems(itemEntities: List<ItemEntity>)

        /**
         * Update a item.
         *
         * @param itemEntity item to be updated
         * @return the number of items updated. This should always be 1.
         */
        @Update
        suspend fun updateItem(itemEntity: ItemEntity): Int

        /**
         * Update a list of items.
         *
         * @param itemEntity item to be updated
         * @return the number of items updated. This should always be 1.
         */
        @Update
        suspend fun updateItems(itemEntities: List<ItemEntity>): Int

        /**
         * Delete a item by id.
         *
         * @return the number of items deleted. This should always be 1.
         */
        @Query("DELETE FROM Items WHERE id = :itemId")
        suspend fun deleteItemById(itemId: String): Int

        /**
         * Delete all items.
         */
        @Query("DELETE FROM Items")
        suspend fun deleteItems()

    }