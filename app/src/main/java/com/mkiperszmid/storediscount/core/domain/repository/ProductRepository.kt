package com.mkiperszmid.storediscount.core.domain.repository

import com.mkiperszmid.storediscount.core.domain.model.ProductItem

interface ProductRepository {
    suspend fun getProducts(): Result<List<ProductItem>>
    suspend fun getCart(): List<ProductItem> // TODO: Separate in 2 repositories, Cart and Home
}
