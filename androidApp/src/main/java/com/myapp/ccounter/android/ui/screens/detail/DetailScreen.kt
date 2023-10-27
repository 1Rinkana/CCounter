package com.myapp.ccounter.android.ui.screens.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.myapp.ccounter.android.ui.items.LoadingProductDetailsItem
import com.myapp.ccounter.android.ui.items.ProductDetailsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    uiState: DetailScreenState,
    onBackPressed: () -> Unit,
    saveProduct: () -> Unit,
    deleteProduct: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(
                        text = "Description",
                        modifier = Modifier,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                },
                modifier = Modifier,
                navigationIcon = {
                    IconButton(
                        onClick = { onBackPressed() },
                        modifier = Modifier,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Go back",
                            modifier = Modifier,
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            when (uiState.hasProductBeenSaved) {
                                false -> saveProduct()
                                else -> deleteProduct()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = when (uiState.hasProductBeenSaved) {
                                false -> Icons.Filled.Add
                                else -> Icons.Outlined.Delete
                            }, contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    scrolledContainerColor = MaterialTheme.colorScheme.background,
                ),
                scrollBehavior = scrollBehavior,
            )
        },
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .padding(paddingValues)
        ) {
            item {
                when (uiState.loading) {
                    true -> LoadingProductDetailsItem()
                    else -> {
                        uiState.product?.let {
                            ProductDetailsItem(product = uiState.product!!)
                        }
                    }
                }
            }
        }
    }
}
