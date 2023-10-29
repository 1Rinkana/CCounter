package com.myapp.ccounter.data.local.product

import com.myapp.ccounter.data.local.LocalDataSource
import com.myapp.ccounter.database.ProductDatabase
import com.myapp.ccounter.domain.model.Product

class SqlDelightProductDataSource(db: ProductDatabase): LocalDataSource {
    private val productQueries = db.productQueries
    private val nutrientQueries = db.nutrientQueries

    override suspend fun insertProduct(product: Product) {
        productQueries.insertProductEntity(
            id = product.id,
            aisle = product.aisle,
            image = product.image,
            ingredientList = product.ingredientList,
            percentCarbs = product.nutrition.caloricBreakdown.percentCarbs,
            percentFat = product.nutrition.caloricBreakdown.percentFat,
            percentProtein = product.nutrition.caloricBreakdown.percentProtein,
            price = product.price,
            title = product.title
        )

        product.nutrition.nutrients.forEach { nutrient ->
            product.id.let {
                nutrientQueries.insertNutrientEntity(
                    productId = it,
                    name = nutrient.name,
                    amount = nutrient.amount,
                    unit = nutrient.unit,
                    percentOfDailyNeeds = nutrient.percentOfDailyNeeds,
                )
            }
        }
    }

    override suspend fun getProductById(id: Long): Product? {
        return productQueries
            .getProductById(id)
            .executeAsOneOrNull()
            ?.toProduct(
                nutrientQueries
                    .getNutrientsById(id)
                    .executeAsList()
                    .map { nutrientEntity ->  nutrientEntity.toNutrient() }
            )
    }

    override suspend fun getAllProducts(): List<Product> {
        return productQueries
            .getAllProducts()
            .executeAsList()
            .map { productEntity ->
                productEntity.toProduct(
                    nutrientQueries
                        .getNutrientsById(productEntity.id)
                        .executeAsList()
                        .map { nutrientEntity ->  nutrientEntity.toNutrient() }
                )
            }
    }

    override suspend fun deleteProductById(id: Long) {
        productQueries.deleteProduct(id)
        nutrientQueries.deleteNutrients(id)
    }

    override suspend fun getProductsByTitle(title: String): List<Product> {
        return productQueries
            .getProductsByTitle(title)
            .executeAsList()
            .map { productEntity ->
                productEntity.toProduct(
                    nutrientQueries
                        .getNutrientsById(productEntity.id)
                        .executeAsList()
                        .map { nutrientEntity ->  nutrientEntity.toNutrient() }
                )
            }
    }
}
