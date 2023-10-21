package com.myapp.ccounter.data.repository

import com.myapp.ccounter.data.remote.RemoteDataSource
import com.myapp.ccounter.data.util.toProduct
import com.myapp.ccounter.data.util.toProductsListItem
import com.myapp.ccounter.domain.model.Product
import com.myapp.ccounter.domain.model.ProductItem
import com.myapp.ccounter.domain.repository.ProductRepository

internal class ProductRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
): ProductRepository {
    override suspend fun getProductsList(productsName: String): List<ProductItem> {
        return remoteDataSource.getProductsList(productsName = productsName).products.map {
            it.toProductsListItem()
        }
    }

    override suspend fun getProduct(productId: Int): Product {
        return remoteDataSource.getProduct(productId = productId).results.toProduct()
    }
}
