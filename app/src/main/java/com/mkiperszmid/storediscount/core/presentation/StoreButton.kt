package com.mkiperszmid.storediscount.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
    TextButton(
        onClick = onClick,
        modifier = modifier.clip(RoundedCornerShape(10.dp)).background(color = Greenish)
    ) {
        Text(text = text, color = Color.White)
    }
}

@Preview
@Composable
fun StoreButtonPreview() {
    StoreButton(text = "Go to cart", onClick = { })
}
