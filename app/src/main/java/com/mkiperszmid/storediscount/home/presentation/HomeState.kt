package com.mkiperszmid.storediscount.home.presentation

import com.mkiperszmid.storediscount.core.domain.model.ProductItem

data class HomeState(
    val products: List<ProductItem> = emptyList(),
    val isLoading: Boolean = false
)
