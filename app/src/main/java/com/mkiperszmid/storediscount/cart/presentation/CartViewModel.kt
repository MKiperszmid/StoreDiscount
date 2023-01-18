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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
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
            repository.getCart().collectLatest {
                val sorted = it.sortedBy { cartItem -> cartItem.item.code }
                state = state.copy(
                    items = sorted,
                    isLoading = false
                )
                calculatePrice(it)
                val promotionDiscount = promotionDiscount(it)
                val bulkDiscount = bulkDiscount(it)
                val discount = bulkDiscount + promotionDiscount
                state = state.copy(
                    discount = discount,
                    totalPrice = state.originalPrice - discount
                )
            }
        }
    }

    private fun calculatePrice(items: List<CartItem>) {
        var price = 0.0
        items.forEach {
            price += it.amount * it.item.price
        }

        state = state.copy(
            originalPrice = price
        )
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
