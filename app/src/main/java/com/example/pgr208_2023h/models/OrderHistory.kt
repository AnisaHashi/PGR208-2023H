package com.example.pgr208_2023h.models

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

data class OrderHistory(
    val date: LocalDateTime,
    val products: List<Product>,
    val sumPrice: Double,
    val sumItems: Int,
)