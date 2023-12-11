package com.example.pgr208_2023h.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.pgr208_2023h.models.OrderHistory
import com.example.pgr208_2023h.models.Product
import java.time.LocalDate

class OrderHistoryviewModel : ViewModel() {
    private val _cartList = mutableStateListOf<OrderHistory>()
    val orderHistoryItems: List<OrderHistory>
        get() = _cartList

    fun addOrderHistoryItem( orderHistory: OrderHistory){
        _cartList.add(orderHistory)
    }

}