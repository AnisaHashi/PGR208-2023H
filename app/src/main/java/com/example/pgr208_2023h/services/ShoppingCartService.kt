package com.example.pgr208_2023h.services

import com.example.pgr208_2023h.models.Product

class ShoppingCartService {
    var cart = mutableListOf<Product>()

    fun addToCart(product: Product){
        println("Adding to cart: " + product)
        cart.add(product)
    }

    fun deleteFromCart(product: Product){
        cart.remove(product)
    }

    fun getShoppingCart(): List<Product>{
        return cart
    }

}