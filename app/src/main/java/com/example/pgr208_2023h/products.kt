package com.example.pgr208_2023h

data class Product(
    val id: Int,
    val title: String,
    val category: String,
    val price: Double,
    val image: String
)

val product1 =
    Product(
        id = 1,
        title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
        category = "men's clothing",
        price = 109.95,
        image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg"
    )

val product2 =
    Product(
        id = 2,
        title = "Mens Casual Premium Slim Fit T-Shirts",
        category = "men's clothing",
        price = 22.3,
        image = "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg"
    )

val product3 =
    Product(
        id = 3,
        title = "Mens Cotton Jacket",
        category = "men's clothing",
        price = 55.99,
        image = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg"
    )


fun getProducts(): List<Product> {
    return listOf(product1, product2, product3)
}