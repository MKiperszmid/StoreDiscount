package com.mkiperszmid.storediscount.cart.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mkiperszmid.storediscount.core.domain.model.CartItem
import com.mkiperszmid.storediscount.core.domain.model.ProductCode
import com.mkiperszmid.storediscount.core.domain.model.ProductItem
import com.mkiperszmid.storediscount.ui.theme.Blueish
import com.mkiperszmid.storediscount.ui.theme.DarkBlueish

@Composable
fun CartProductItem(
    cartItem: CartItem,
    onAddItem: () -> Unit,
    onMinusItem: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).background(Color.White).padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = cartItem.item.code.icon,
                tint = DarkBlueish,
                contentDescription = "icon",
                modifier = Modifier.background(
                    Blueish,
                    CircleShape
                ).padding(10.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = cartItem.item.name)
                Text(
                    text = String.format("$%.2f", cartItem.item.price),
                    fontWeight = FontWeight.Bold
                )
            }
        }
        CartItemIncrease(
            amount = cartItem.amount,
            onAddClick = onAddItem,
            onMinusClick = onMinusItem
        )
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
