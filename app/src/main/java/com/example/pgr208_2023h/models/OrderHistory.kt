package com.example.pgr208_2023h.models

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID


data class OrderHistory(
    val id: String = UUID.randomUUID().toString(),

    val date: LocalDateTime,
    val items: List<String>,
    val sumPrice: Double,
    val sumItems: Int,
)