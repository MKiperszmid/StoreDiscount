package com.mkiperszmid.storediscount.cart.presentation

import com.mkiperszmid.storediscount.core.domain.model.CartItem

data class CartState(
    val items: List<CartItem> = emptyList(),
    val discount: Double = 0.0,
    val originalPrice: Double = 0.0,
    val totalPrice: Double = 0.0,
    val isLoading: Boolean = false
)
