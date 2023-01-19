package com.mkiperszmid.storediscount.cart.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CartItemIncrease(
    amount: Int,
    onAddClick: () -> Unit,
    onMinusClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        IconButton(onClick = onMinusClick) {
            Icon(imageVector = Icons.Default.Remove, contentDescription = "Remove")
        }
        Text(text = "${amount}")
        IconButton(onClick = onAddClick) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }
}
