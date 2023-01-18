package com.mkiperszmid.storediscount.core.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mkiperszmid.storediscount.core.domain.model.ProductCode

@Entity
data class ProductItemEntity(
    @PrimaryKey(autoGenerate = false)
    val code: ProductCode,
    val price: Double,
    val name: String,
    val amount: Int
)
