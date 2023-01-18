package com.mkiperszmid.storediscount.cart.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkiperszmid.storediscount.cart.domain.usecase.CartUseCases
import com.mkiperszmid.storediscount.core.domain.model.CartItem
import com.mkiperszmid.storediscount.core.domain.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: ProductRepository,
    private val cartUseCases: CartUseCases
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
                val originalPrice = cartUseCases.calculatePrice(it)
                val discount = cartUseCases.calculateDiscounts(it)
                state = state.copy(
                    originalPrice = originalPrice,
                    discount = discount,
                    totalPrice = originalPrice - discount
                )
            }
        }
    }

    fun onAddItem(item: CartItem) {
        viewModelScope.launch {
            repository.addItemToCart(item.item) // TODO: Technically speaking, since we are usinhg usecases, we can also move both of these (add + minus) to usecases
        }
    }

    fun onMinusItem(item: CartItem) {
        viewModelScope.launch {
            repository.minusItemToCart(item.item)
        }
    }
}
