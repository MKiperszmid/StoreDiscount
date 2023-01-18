package com.mkiperszmid.storediscount.core.domain.model

data class ProductItem(
    val code: ProductCode,
    val name: String,
    val price: Double
)

enum class ProductCode {
    VOUCHER,
    TSHIRT,
    MUG
}
