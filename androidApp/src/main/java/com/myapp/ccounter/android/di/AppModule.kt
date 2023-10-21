package com.myapp.ccounter.android.di

import com.myapp.ccounter.android.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { SearchViewModel(get()) }
}