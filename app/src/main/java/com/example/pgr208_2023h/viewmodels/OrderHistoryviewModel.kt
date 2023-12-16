package com.example.pgr208_2023h.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pgr208_2023h.data.OrderHistory
import com.example.pgr208_2023h.data.OrderHistoryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

/*

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.pgr208_2023h.models.OrderHistory


class OrderHistoryviewModel : ViewModel() {
    private val _cartList = mutableStateListOf<OrderHistory>()
    val orderHistoryItems: List<OrderHistory>
        get() = _cartList

    fun addOrderHistoryItem( orderHistory: OrderHistory){
        _cartList.add(orderHistory)
    }

}
*/


class OrderHistoryviewModel(private val orderHistoryDao: OrderHistoryDao): ViewModel() {

    val orders: Flow<List<OrderHistory>> = orderHistoryDao.getAllOrderes()



    fun addOrder(order: OrderHistory) {
        viewModelScope.launch(Dispatchers.IO) {
            orderHistoryDao.addOrder(order)
        }
    }
}