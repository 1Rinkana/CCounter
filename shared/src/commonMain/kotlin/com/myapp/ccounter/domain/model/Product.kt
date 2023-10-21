package com.myapp.ccounter.domain.model

import com.myapp.ccounter.data.remote.Nutrition

data class Product(
    val id: Long,
    val title: String,
    val ingredientList: String,
    val aisle: String,
    val nutrition: Nutrition,
    val price: Double,
)
