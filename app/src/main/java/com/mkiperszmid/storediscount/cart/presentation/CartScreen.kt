package com.mkiperszmid.storediscount.cart.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mkiperszmid.storediscount.cart.presentation.components.CartPrice
import com.mkiperszmid.storediscount.cart.presentation.components.CartProductItem

@Composable
fun CartScreen(
    viewmodel: CartViewModel = hiltViewModel()
) {
    val state = viewmodel.state
    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (state.items.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Cart",
                fontSize = 20.sp,
                modifier = Modifier.padding(16.dp)
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                items(state.items) {
                    CartProductItem(
                        cartItem = it,
                        onAddItem = {
                            viewmodel.onAddItem(it)
                        },
                        onMinusItem = {
                            viewmodel.onMinusItem(it)
                        }
                    )
                }
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                Divider(modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp))
                CartPrice(title = "Original Price", price = state.originalPrice)
                CartPrice(title = "Discount", price = state.discount)
                CartPrice(title = "Total", price = state.totalPrice)
            }
        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = "empty cart")
            Text(text = "Your cart looks empty!")
        }
    }
}
