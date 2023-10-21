package com.myapp.ccounter.android

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.myapp.ccounter.android.common.Detail
import com.myapp.ccounter.android.common.Search
import com.myapp.ccounter.android.common.productDestination
import com.myapp.ccounter.android.detail.DetailScreen
import com.myapp.ccounter.android.detail.DetailViewModel
import com.myapp.ccounter.android.search.SearchScreen
import com.myapp.ccounter.android.search.SearchViewModel
import kotlinx.coroutines.runBlocking
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CCounterApp() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    val backStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },

    ) {innerPaddings ->
        NavHost(
            navController = navController,
            modifier = Modifier.padding(innerPaddings),
            startDestination = Search.routeWithArgs
        ){
            composable(Search.routeWithArgs){
                val searchViewModel: SearchViewModel = koinViewModel()
                SearchScreen(
                    uiState = searchViewModel.uiState,
                    searchForProduct = {
                        searchViewModel.searchForProducts()
                    },
                    navigateToDetail = {
                        navController.navigate(
                            "${Detail.route}/${it.id}"
                        )
                    }
                )
            }

            composable(Detail.routeWithArgs, arguments = Detail.arguments){
                val productId = it.arguments?.getInt("id") ?: 0
                val detailViewModel: DetailViewModel = koinViewModel(
                    parameters = { parametersOf(productId) }
                )

                DetailScreen(uiState = detailViewModel.uiState)
            }
        }
    }
}
