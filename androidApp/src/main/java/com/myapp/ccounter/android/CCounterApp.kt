package com.myapp.ccounter.android

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.myapp.ccounter.android.common.Detail
import com.myapp.ccounter.android.common.Main
import com.myapp.ccounter.android.ui.screens.detail.DetailScreen
import com.myapp.ccounter.android.ui.screens.detail.DetailViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CCounterApp() {
    val navHostRoot = rememberNavController()

    val systemUiController = rememberSystemUiController()

    NavHost(
        navController = navHostRoot,
        modifier = Modifier,
        startDestination = Main.routeWithArgs
    ) {

        composable(Main.route) {
            MainScreen(navHostRoot)
        }

        composable(Detail.routeWithArgs, arguments = Detail.arguments) {
            val productId = it.arguments?.getInt("productId") ?: 0
            val detailViewModel: DetailViewModel = koinViewModel(
                parameters = { parametersOf(productId) }
            )
            systemUiController.setNavigationBarColor(Color.Transparent)

            DetailScreen(
                uiState = detailViewModel.uiState,
                onBackPressed = {
                    navHostRoot.popBackStack()
                },
                saveProduct = {
                    detailViewModel.saveProduct()
                },
                deleteProduct = {
                    detailViewModel.deleteProduct()
                }
            )
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)
