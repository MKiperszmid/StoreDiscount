package com.mkiperszmid.storediscount.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mkiperszmid.storediscount.core.data.local.entity.ProductItemEntity

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: ProductItemEntity)
    // TODO: We can have ProductItemEntity have a "amount" parameter, and just increase/decrease that number instead of
    // adding new items to the DB

    @Query("SELECT * FROM ProductItemEntity WHERE amount > 0")
    suspend fun getCart(): List<ProductItemEntity>

    @Query("SELECT amount FROM ProductItemEntity where code == :code")
    suspend fun getItemCount(code: String): Int?
}
