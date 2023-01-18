package com.mkiperszmid.storediscount.cart.domain.usecase

import com.mkiperszmid.storediscount.core.domain.model.CartItem

class CalculatePrice {
    operator fun invoke(items: List<CartItem>): Double {
        var price = 0.0
        items.forEach {
            price += it.amount * it.item.price
        }
        return price
    }
}