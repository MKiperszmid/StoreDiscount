package com.mkiperszmid.storediscount.core.domain.repository

import com.mkiperszmid.storediscount.core.domain.model.CartItem
import com.mkiperszmid.storediscount.core.domain.model.ProductItem
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getProducts(): Result<List<ProductItem>>
    fun getCart(): Flow<List<CartItem>> // TODO: Separate in 2 repositories, Cart and Home

    suspend fun addItemToCart(item: ProductItem)
    suspend fun minusItemToCart(item: ProductItem)
}
