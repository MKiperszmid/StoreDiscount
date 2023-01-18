package com.mkiperszmid.storediscount.cart.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Discount: ")
                    Text(text = "$${state.discount}")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Original Price: ")
                    Text(text = "$${state.originalPrice}")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Total: ")
                    Text(text = "$${state.totalPrice}")
                }
            }
        }
    }

    // TODO: Handle empty state items
}
