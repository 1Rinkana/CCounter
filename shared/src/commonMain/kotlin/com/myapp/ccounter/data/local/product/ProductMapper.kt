package com.myapp.ccounter.data.local.product

import com.myapp.ccounter.data.remote.CaloricBreakdown
import com.myapp.ccounter.data.remote.Nutrient
import com.myapp.ccounter.data.remote.Nutrition
import com.myapp.ccounter.domain.model.Product
import database.NutrientEntity
import database.ProductEntity

fun ProductEntity.toProduct(nutrients: List<Nutrient>): Product {
    return Product(
        id = id,
        ingredientList = ingredientList,
        title = title,
        aisle = aisle,
        nutrition = Nutrition(
            nutrients = nutrients,
            caloricBreakdown = CaloricBreakdown(
                percentProtein = percentProtein,
                percentCarbs = percentCarbs,
                percentFat = percentFat,
            )
        ),
        price = price,
    )
}

fun NutrientEntity.toNutrient(): Nutrient {
    return Nutrient(
        name = name,
        amount = amount,
        unit = unit,
        percentOfDailyNeeds = percentOfDailyNeeds,
    )
}