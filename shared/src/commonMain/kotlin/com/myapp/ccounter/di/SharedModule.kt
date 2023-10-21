package com.myapp.ccounter.di

import com.myapp.ccounter.data.remote.ProductService
import com.myapp.ccounter.data.remote.RemoteDataSource
import com.myapp.ccounter.data.repository.ProductRepositoryImpl
import com.myapp.ccounter.domain.repository.ProductRepository
import com.myapp.ccounter.domain.usecase.GetProductUseCase
import com.myapp.ccounter.domain.usecase.GetProductsListUseCase
import com.myapp.ccounter.util.provideDispatcher
import org.koin.dsl.module

private val dataModule = module {
    factory { RemoteDataSource(get(), get()) }
    factory { ProductService() }
}

private val utilityModule = module {
    factory { provideDispatcher() }
}

private val domainModule = module {
    single<ProductRepository> { ProductRepositoryImpl(get()) }
    factory { GetProductsListUseCase() }
    factory { GetProductUseCase() }
}

private val sharedModules = listOf(domainModule, dataModule, utilityModule)

fun getSharedModules() = sharedModules
