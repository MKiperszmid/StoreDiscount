package com.mkiperszmid.storediscount.core.data.local

import androidx.room.*
import com.mkiperszmid.storediscount.core.data.local.entity.ProductItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: ProductItemEntity)

    @Query("SELECT * FROM ProductItemEntity WHERE amount > 0")
    fun getCart(): Flow<List<ProductItemEntity>>

    @Query("SELECT amount FROM ProductItemEntity where code == :code")
    suspend fun getItemCount(code: String): Int?

    @Delete
    suspend fun removeItem(item: ProductItemEntity)
}
