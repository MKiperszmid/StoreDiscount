package com.mkiperszmid.storediscount.cart.domain.usecase

import com.mkiperszmid.storediscount.core.domain.model.CartItem
import com.mkiperszmid.storediscount.core.domain.model.ProductCode

class BulkDiscount {
    companion object {
        private const val DISCOUNT_PRICE = 19.0
        private const val MINIMUM_AMOUNT = 3
    }

    operator fun invoke(items: List<CartItem>): Double {
        val shirts = items.firstOrNull { it.item.code == ProductCode.TSHIRT } ?: return 0.0
        val itemPrice = if (shirts.amount >= MINIMUM_AMOUNT) DISCOUNT_PRICE else shirts.item.price
        return shirts.amount * itemPrice
    }
}
