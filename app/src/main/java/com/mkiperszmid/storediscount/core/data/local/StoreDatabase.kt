package com.mkiperszmid.storediscount.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mkiperszmid.storediscount.core.data.local.entity.ProductItemEntity

@Database(entities = [ProductItemEntity::class], version = 1)
abstract class StoreDatabase : RoomDatabase() {
    abstract val dao: ProductDao
}
