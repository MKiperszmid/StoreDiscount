package com.mkiperszmid.storediscount.core.data

import com.mkiperszmid.storediscount.core.data.local.ProductDao
import com.mkiperszmid.storediscount.core.data.mapper.toDomain
import com.mkiperszmid.storediscount.core.data.mapper.toEntity
import com.mkiperszmid.storediscount.core.data.remote.ProductApi
import com.mkiperszmid.storediscount.core.domain.model.CartItem
import com.mkiperszmid.storediscount.core.domain.model.ProductItem
import com.mkiperszmid.storediscount.core.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

    override fun getCart(): Flow<List<CartItem>> {
        val cart = dao.getCart()
        return cart.map { cartEntity -> cartEntity.map { it.toDomain() } }
    }

    override suspend fun addItemToCart(item: ProductItem) {
        // TODO: See if there's a better way
        val currentAmount = getItemCount(item)
        val newAmount = currentAmount + 1
        val cartItem = CartItem(
            item = item,
            amount = newAmount
        )
        dao.addItem(cartItem.toEntity())
    }

    override suspend fun minusItemToCart(item: ProductItem) {
        // TODO: See if there's a better way
        val currentAmount = getItemCount(item)
        if (currentAmount <= 0) return

        val newAmount = currentAmount - 1
        val cartItem = CartItem(
            item = item,
            amount = newAmount
        )
        val entity = cartItem.toEntity()

        if (newAmount == 0) {
            dao.removeItem(entity)
        } else {
            dao.addItem(entity)
        }
    }

    private suspend fun getItemCount(item: ProductItem): Int {
        val code = item.code.name
        return dao.getItemCount(code) ?: 0
    }
}
