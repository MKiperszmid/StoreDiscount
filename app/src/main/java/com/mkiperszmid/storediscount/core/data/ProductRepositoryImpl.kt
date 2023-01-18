package com.mkiperszmid.storediscount.core.data

import com.mkiperszmid.storediscount.core.data.local.ProductDao
import com.mkiperszmid.storediscount.core.data.mapper.toDomain
import com.mkiperszmid.storediscount.core.data.mapper.toEntity
import com.mkiperszmid.storediscount.core.data.remote.ProductApi
import com.mkiperszmid.storediscount.core.domain.model.CartItem
import com.mkiperszmid.storediscount.core.domain.model.ProductItem
import com.mkiperszmid.storediscount.core.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val api: ProductApi,
    private val dao: ProductDao
) : ProductRepository {
    override suspend fun getProducts(): Result<List<ProductItem>> {
        return try {
            val items = api.getProducts()
            val mapped = items.products.map { it.toDomain() }
            Result.success(mapped)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getCart(): List<CartItem> {
        val cart = dao.getCart()
        return cart.map { it.toDomain() }
    }

    override suspend fun addItemToCart(item: ProductItem) {
        // TODO: See if there's a better way
        val code = item.code.name
        val currentAmount = dao.getItemCount(code) ?: 0
        val newAmount = currentAmount + 1
        val cartItem = CartItem(
            item = item,
            amount = newAmount
        )
        dao.addItem(cartItem.toEntity())
    }

    override suspend fun minusItemToCart(item: ProductItem) {
        // TODO: See if there's a better way
        val code = item.code.name
        val currentAmount = dao.getItemCount(code) ?: 0
        val newAmount = currentAmount - 1
        val cartItem = CartItem(
            item = item,
            amount = newAmount
        )
        dao.addItem(cartItem.toEntity())
    }
}
