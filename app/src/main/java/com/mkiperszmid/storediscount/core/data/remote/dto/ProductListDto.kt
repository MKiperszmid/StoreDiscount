package com.mkiperszmid.storediscount.core.data.remote.dto

import com.squareup.moshi.Json

data class ProductListDto(
    @field:Json(name = "products")
    val products: List<ProductDto>
)
