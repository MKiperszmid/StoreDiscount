package com.mkiperszmid.storediscount.cart.domain.usecase

import com.mkiperszmid.storediscount.core.domain.model.CartItem

class CalculateDiscounts(
    private val bulkDiscount: BulkDiscount,
    private val promotionDiscount: PromotionDiscount
) {
    operator fun invoke(items: List<CartItem>): Double {
        return bulkDiscount(items) + promotionDiscount(items)
    }
}
