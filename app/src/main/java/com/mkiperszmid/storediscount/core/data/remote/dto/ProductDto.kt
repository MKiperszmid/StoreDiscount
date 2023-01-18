package com.mkiperszmid.storediscount.core.data.remote.dto

import com.squareup.moshi.Json

data class ProductDto(
    @field:Json(name = "code")
    val code: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "price")
    val price: Double
)