package com.myapp.ccounter.di

import com.myapp.ccounter.data.local.DatabaseDriverFactory
import com.myapp.ccounter.data.local.LocalDataSource
import com.myapp.ccounter.data.local.product.SqlDelightProductDataSource
import com.myapp.ccounter.database.ProductDatabase
import kotlinx.coroutines.runBlocking

class DatabaseModule {
    private val factory by lazy { DatabaseDriverFactory() }

    val productDataSource: LocalDataSource by lazy {
        val driver = runBlocking {
            factory.createDriver(ProductDatabase.Schema)
        }
        SqlDelightProductDataSource(ProductDatabase(driver))
    }
}