package com.mkiperszmid.storediscount.cart.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkiperszmid.storediscount.cart.domain.usecase.BulkDiscount
import com.mkiperszmid.storediscount.cart.domain.usecase.PromotionDiscount
import com.mkiperszmid.storediscount.core.domain.model.CartItem
import com.mkiperszmid.storediscount.core.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val promotionDiscount: PromotionDiscount,
    private val bulkDiscount: BulkDiscount
) : ViewModel() {
    var state by mutableStateOf(CartState())
        private set

    init {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val cartItems = repository.getCart()
            val promotionDiscount = promotionDiscount(cartItems)
            val bulkDiscount = bulkDiscount(cartItems)
            state = state.copy(isLoading = false, items = cartItems)
        }
    }

    fun onAddItem(item: CartItem) {
        viewModelScope.launch {
            repository.addItemToCart(item.item)
        }
    }

    fun onMinusItem(item: CartItem) {
        viewModelScope.launch {
            repository.minusItemToCart(item.item)
        }
    }
}
