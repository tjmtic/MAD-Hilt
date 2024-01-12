package com.abyxcz.data.util

import androidx.room.TypeConverter
import com.abyxcz.data.entity.ItemEntity
import com.abyxcz.data.model.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromString(value: String?): ArrayList<String?>? {
        val listType = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun itemFromItemEntity(itemEntity: ItemEntity): Item {
        return Item(
            itemId = itemEntity.itemId,
            title = itemEntity.title,
            isCompleted = itemEntity.isCompleted,
            description = itemEntity.description
        )
    }

    //@TypeConverter
    //fun itemEntityfromItem(item: Item): ItemEntity {
    //    return ItemEntity(itemId = item.itemId)
    //}
}