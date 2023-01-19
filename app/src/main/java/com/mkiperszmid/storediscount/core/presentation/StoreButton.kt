package com.mkiperszmid.storediscount.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mkiperszmid.storediscount.ui.theme.Greenish

@Composable
fun StoreButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = if (text.length == 1) CircleShape else RoundedCornerShape(10.dp)
    TextButton(
        onClick = onClick,
        modifier = modifier.background(color = Greenish, shape = shape)
    ) {
        Text(text = text, color = Color.White)
    }
}

@Preview
@Composable
fun StoreButtonPreview() {
    StoreButton(text = "Go to cart", onClick = { })
}
