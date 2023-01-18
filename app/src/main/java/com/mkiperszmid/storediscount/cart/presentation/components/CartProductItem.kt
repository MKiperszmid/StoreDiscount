package com.mkiperszmid.storediscount.cart.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mkiperszmid.storediscount.core.domain.model.CartItem
import com.mkiperszmid.storediscount.core.domain.model.ProductCode
import com.mkiperszmid.storediscount.core.domain.model.ProductItem
import com.mkiperszmid.storediscount.core.presentation.StoreButton

@Composable
fun CartProductItem(
    cartItem: CartItem,
    onAddItem: () -> Unit,
    onMinusItem: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = cartItem.item.name)
            Text(text = "$${cartItem.item.price}")
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                StoreButton(text = "+", onClick = onAddItem)
                StoreButton(text = "-", onClick = onMinusItem)
            }
            Text(text = "x${cartItem.amount}")
        }
    }
}

@Preview
@Composable
fun CartProductItemPreview() {
    CartProductItem(
        cartItem = CartItem(
            item = ProductItem(
                code = ProductCode.VOUCHER,
                name = "Voucher",
                price = 5.0
            ),
            amount = 3
        ),
        {},
        {}
    )
}
