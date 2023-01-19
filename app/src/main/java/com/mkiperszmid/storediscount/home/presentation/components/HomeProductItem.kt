package com.mkiperszmid.storediscount.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mkiperszmid.storediscount.ui.theme.Grayish

@Composable
fun HomeProductItem(
    title: String,
    price: Double,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth().clip(RoundedCornerShape(8.dp)).background(Grayish).clickable {
            onClick()
        }.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Text(text = String.format("$%.2f", price), fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
fun HomeProductItemPreview() {
    HomeProductItem(title = "Coffee Mug", price = 7.5, onClick = {})
}
