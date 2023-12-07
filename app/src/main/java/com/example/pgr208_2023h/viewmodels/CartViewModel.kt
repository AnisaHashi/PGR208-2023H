package com.example.pgr208_2023h.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pgr208_2023h.models.Product
import com.example.pgr208_2023h.services.Productservice
import kotlinx.coroutines.launch

class CartViewModel : ViewModel() {
    private val _cartList = mutableStateListOf<Product>()
    val cartItems: List<Product>
        get() = _cartList

    fun addCartItem(product: Product){
        _cartList.add(product)
    }
    fun deleteCartItem(product: Product){
        _cartList.remove(product)
    }

}