package com.myapp.ccounter.android.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import com.myapp.ccounter.android.ui.screens.detail.DetailViewModel
import com.myapp.ccounter.android.ui.screens.saved.SavedProductsViewModel
import com.myapp.ccounter.android.ui.screens.search.SearchViewModel
import com.myapp.ccounter.data.local.DatabaseDriverFactory
import com.myapp.ccounter.data.local.LocalDataSource
import com.myapp.ccounter.data.local.product.SqlDelightProductDataSource
import com.myapp.ccounter.database.ProductDatabase
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module




val appModule = module {
    single { runBlocking { provideSqlDriver(get()) } }
    single { provideNoteProductSource(get()) }

    viewModel { SearchViewModel(get()) }
    viewModel { params ->
        DetailViewModel(
            getProductUseCase = get(),
            productDataSource = get(),
            productId = params.get()
        )
    }
    viewModel { SavedProductsViewModel(get()) }
}

suspend fun provideSqlDriver(app: Application): SqlDriver {
    return DatabaseDriverFactory(app).createDriver(schema = ProductDatabase.Schema)
}

fun provideNoteProductSource(driver: SqlDriver): LocalDataSource {
    return SqlDelightProductDataSource(ProductDatabase(driver))
}




