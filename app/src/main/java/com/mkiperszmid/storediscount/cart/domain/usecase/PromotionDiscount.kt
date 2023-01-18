package com.mkiperszmid.storediscount.cart.domain.usecase

import com.mkiperszmid.storediscount.core.domain.model.CartItem
import com.mkiperszmid.storediscount.core.domain.model.ProductCode
import kotlin.math.floor

class PromotionDiscount {
    companion object {
        private const val PROMOTION_AMOUNT = 2f
    }

    operator fun invoke(items: List<CartItem>): Double {
        val voucher = items.firstOrNull { it.item.code == ProductCode.VOUCHER } ?: return 0.0
        return floor(voucher.amount / PROMOTION_AMOUNT) * voucher.item.price
    }
}
