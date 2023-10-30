package com.myapp.ccounter.android

import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.myapp.ccounter.android.common.Detail
import com.myapp.ccounter.android.common.Saved
import com.myapp.ccounter.android.common.Search
import com.myapp.ccounter.android.ui.screens.saved.SavedProductsScreen
import com.myapp.ccounter.android.ui.screens.saved.SavedProductsViewModel
import com.myapp.ccounter.android.ui.screens.search.SearchScreen
import com.myapp.ccounter.android.ui.screens.search.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen(
    navHostRoot: NavHostController,
) {
    val systemUiController = rememberSystemUiController()

    val screens = listOf(
        BottomNavigationItem(
            title = "search",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search,
        ),
        BottomNavigationItem(
            title = "saved",
            selectedIcon = Icons.Filled.ShoppingCart,
            unselectedIcon = Icons.Outlined.ShoppingCart,
        ),
    )

    val navHostMain = rememberNavController()

    var selectedScreenIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                screens.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedScreenIndex == index,
                        onClick = {
                            selectedScreenIndex = index
                            navHostMain.navigate(item.title)
                        },
                        label = {
                            Text(text = item.title.capitalize())
                        },
                        alwaysShowLabel = true,
                        icon = {
                            Icon(
                                imageVector = if (index == selectedScreenIndex) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                    )
                }
            }
        },
    ) { paddingValues ->
        NavHost(
            navController = navHostMain,
            modifier = Modifier
                .padding(bottom = paddingValues.calculateStartPadding(LayoutDirection.Ltr)),
            startDestination = Search.routeWithArgs
        ) {
            composable(Search.routeWithArgs){
                val searchViewModel: SearchViewModel = koinViewModel()
                systemUiController.setNavigationBarColor(color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.01f))

                SearchScreen(
                    uiState = searchViewModel.uiState,
                    searchForProduct = {
                        searchViewModel.searchForProducts()
                    },
                    navigateToDetail = {
                        navHostRoot.navigate(
                            "${Detail.route}/${it.id}"
                        )
                    },
                )
            }

            composable(Saved.routeWithArgs){
                val savedProductsViewModel: SavedProductsViewModel = koinViewModel()
                systemUiController.setNavigationBarColor(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.01f))

                SavedProductsScreen(
                    uiState = savedProductsViewModel.uiState,
                    navigateToDetail = { id ->
                        navHostRoot.navigate(
                            "${Detail.route}/${id}"
                        )
                    },
                    refreshProductList = {
                        savedProductsViewModel.getAllSavedProducts()
                    }
                )
            }
        }
    }
}

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)
