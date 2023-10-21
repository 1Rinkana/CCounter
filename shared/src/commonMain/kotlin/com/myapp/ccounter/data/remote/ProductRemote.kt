package com.myapp.ccounter.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductsListItemRemote(
    val id: Long,
    val title: String,
    @SerialName("image")
    val imageUrl: String,
)

@Serializable
data class ProductRemote(
    val id: Long,
    val title: String,
    val ingredientList: String,
    val aisle: String,
    val nutrition: Nutrition,
    val price: Double,
)

@Serializable
data class Nutrition(
    val nutrients: List<Nutrient>,
    val caloricBreakdown: CaloricBreakdown,
)

@Serializable
data class Nutrient(
    val name: String,
    val amount: Long,
    val unit: String,
    val percentOfDailyNeeds: Double,
)

@Serializable
data class CaloricBreakdown(
    val percentProtein: Double,
    val percentFat: Double,
    val percentCarbs: Double,
)
