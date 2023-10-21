package com.myapp.ccounter.domain.usecase

import com.myapp.ccounter.domain.model.ProductItem
import com.myapp.ccounter.domain.repository.ProductRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetProductsListUseCase: KoinComponent {
    private val repository: ProductRepository by inject()

    suspend operator fun invoke(productsName: String): List<ProductItem> {
        return repository.getProductsList(productsName = productsName)
    }
}
