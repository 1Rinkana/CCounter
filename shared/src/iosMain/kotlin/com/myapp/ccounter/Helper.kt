package com.myapp.ccounter

import app.cash.sqldelight.db.SqlDriver
import com.myapp.ccounter.data.local.DatabaseDriverFactory
import com.myapp.ccounter.data.local.LocalDataSource
import com.myapp.ccounter.data.local.product.SqlDelightProductDataSource
import com.myapp.ccounter.database.ProductDatabase
import com.myapp.ccounter.di.getSharedModules
import kotlinx.coroutines.runBlocking
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(){
    startKoin {
        modules(appModule + getSharedModules())
    }
}

val appModule = module {
    single { runBlocking { provideSqlDriver() } }
    single { provideNoteProductSource(get()) }
}

suspend fun provideSqlDriver(): SqlDriver {
    return DatabaseDriverFactory().createDriver(schema = ProductDatabase.Schema)
}

fun provideNoteProductSource(driver: SqlDriver): LocalDataSource {
    return SqlDelightProductDataSource(ProductDatabase(driver))
}
