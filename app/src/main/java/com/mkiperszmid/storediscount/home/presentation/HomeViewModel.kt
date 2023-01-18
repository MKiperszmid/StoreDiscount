package com.mkiperszmid.storediscount.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkiperszmid.storediscount.core.domain.model.ProductItem
import com.mkiperszmid.storediscount.core.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    var state by mutableStateOf(HomeState())
        private set

    init {
        getProducts()
    }

    fun onItemClick(productItem: ProductItem) {
        viewModelScope.launch {
            repository.addItemToCart(productItem)
        }
    }

    private fun getProducts() {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true
            )
            repository.getProducts().onSuccess {
                state = state.copy(
                    products = it
                )
            }.onFailure {
                // TODO: Show error
            }
            state = state.copy(
                isLoading = false
            )
        }
    }
}
