package com.myapp.ccounter.domain.repository

import com.myapp.ccounter.domain.model.Product
import com.myapp.ccounter.domain.model.ProductItem

internal interface ProductRepository {
    suspend fun getProductsList(productsName: String): List<ProductItem>

    suspend fun getProduct(productId: Int): Product
}
