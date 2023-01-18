package com.mkiperszmid.storediscount.cart.domain.usecase

data class CartUseCases(
    val calculatePrice: CalculatePrice,
    val calculateDiscounts: CalculateDiscounts
)
