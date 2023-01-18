package com.mkiperszmid.storediscount.cart.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
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
        LazyColumn(modifier = Modifier.fillMaxSize()) {
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
    }

    // TODO: Handle empty state items
}
