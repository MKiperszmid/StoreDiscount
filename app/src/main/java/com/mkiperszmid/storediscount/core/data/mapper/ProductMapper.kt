package com.mkiperszmid.storediscount.core.data.mapper

import com.mkiperszmid.storediscount.core.data.remote.dto.ProductDto
import com.mkiperszmid.storediscount.core.domain.model.ProductCode
import com.mkiperszmid.storediscount.core.domain.model.ProductItem

fun ProductDto.toDomain(): ProductItem {
    return ProductItem(
        code = ProductCode.valueOf(this.code),
        name = this.name,
        price = this.price
    )
}
