package com.mkiperszmid.storediscount.home.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mkiperszmid.storediscount.home.presentation.components.HomeProductItem

@Composable
fun HomeScreen(
    viewmodel: HomeViewModel = hiltViewModel()
) {
    val state = viewmodel.state

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    if (state.products.isNotEmpty()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.products) {
                HomeProductItem(
                    title = it.name,
                    price = it.price,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
        // TODO: Handle error cases
    }
}
