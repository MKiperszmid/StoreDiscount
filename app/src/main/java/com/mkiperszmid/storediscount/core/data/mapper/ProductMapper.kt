package com.mkiperszmid.storediscount.core.data.mapper

import com.mkiperszmid.storediscount.core.data.local.entity.ProductItemEntity
import com.mkiperszmid.storediscount.core.data.remote.dto.ProductDto
import com.mkiperszmid.storediscount.core.domain.model.CartItem
import com.mkiperszmid.storediscount.core.domain.model.ProductCode
import com.mkiperszmid.storediscount.core.domain.model.ProductItem

fun ProductDto.toDomain(): ProductItem {
    return ProductItem(
        code = ProductCode.valueOf(this.code),
        name = this.name,
        price = this.price
    )
}

fun CartItem.toEntity(): ProductItemEntity {
    return ProductItemEntity(
        code = this.item.code,
        price = this.item.price,
        name = this.item.name,
        amount = this.amount
    )
}

fun ProductItemEntity.toDomain(): CartItem {
    val item = ProductItem(
        code = this.code,
        price = this.price,
        name = this.name
    )
    return CartItem(
        item = item,
        amount = this.amount
    )
}
