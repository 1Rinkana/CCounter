package com.myapp.ccounter.android.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

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
        uiState.product?.let {
            val product = uiState.product!!

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .padding(paddingValues)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Card(
                            modifier = modifier
                                .height(220.dp)
                                .width(220.dp),
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Column(
                                modifier = Modifier,
                            ) {
                                Box(
                                    modifier = modifier.weight(1f),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    AsyncImage(
                                        model = product.image,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = modifier
                                            .fillMaxSize()
                                            .clip(
                                                RoundedCornerShape(
                                                    bottomStart = 2.dp,
                                                    bottomEnd = 2.dp
                                                )
                                            ),
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.padding(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = product.title,
                            modifier = Modifier,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Medium,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }

                    Spacer(modifier = Modifier.padding(vertical = 8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            text = "Characteristics:",
                            modifier = Modifier,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }

                    Spacer(modifier = Modifier.padding(8.dp))

                    Card(
                        modifier = Modifier,
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = MaterialTheme.colorScheme.onBackground,
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                        ) {
                            Text(
                                text = "Category:",
                                modifier = Modifier,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.titleMedium,
                            )

                            Text(
                                text = product.aisle,
                                modifier = Modifier,
                                color = MaterialTheme.colorScheme.primary,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Normal,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }

                    Spacer(modifier = Modifier.padding(8.dp))

                    Card(
                        modifier = Modifier,
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = MaterialTheme.colorScheme.onBackground,
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                        ) {
                            Text(
                                text = "Nutrients:",
                                modifier = Modifier,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium,
                            )

                            product.nutrition.nutrients.forEach { nutrient ->
                                if (nutrient.name != product.nutrition.nutrients[0].name) {
                                    Divider(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(vertical = 8.dp),
                                        color = MaterialTheme.colorScheme.surface
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 4.dp),
                                ) {
                                    Text(
                                        text = "${nutrient.name}: ",
                                        modifier = Modifier,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        fontStyle = FontStyle.Italic,
                                        fontWeight = FontWeight.Normal,
                                        style = MaterialTheme.typography.titleMedium,
                                    )

                                    Text(
                                        text = "${nutrient.amount}${nutrient.unit}",
                                        modifier = Modifier,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Normal,
                                        style = MaterialTheme.typography.bodyMedium,
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                ) {
                                    Text(
                                        text = "Percentage of daily needs: ",
                                        modifier = Modifier,
                                        color = MaterialTheme.colorScheme.onBackground,
                                        fontStyle = FontStyle.Italic,
                                        fontWeight = FontWeight.Normal,
                                        style = MaterialTheme.typography.titleMedium,
                                    )

                                    Text(
                                        text = "${nutrient.percentOfDailyNeeds}%",
                                        modifier = Modifier,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Normal,
                                        style = MaterialTheme.typography.bodyMedium,
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.padding(8.dp))

                    Card(
                        modifier = Modifier,
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = MaterialTheme.colorScheme.onBackground,
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                        ) {
                            Text(
                                text = "Caloric breakdown:",
                                modifier = Modifier,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleMedium,
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                            ) {
                                Text(
                                    text = "Protein percentage: ",
                                    modifier = Modifier,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Normal,
                                    style = MaterialTheme.typography.titleMedium,
                                )

                                Text(
                                    text = product.nutrition.caloricBreakdown.percentProtein.toString(),
                                    modifier = Modifier,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Normal,
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }

                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                color = MaterialTheme.colorScheme.surface
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                            ) {
                                Text(
                                    text = "Fat percentage: ",
                                    modifier = Modifier,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Normal,
                                    style = MaterialTheme.typography.titleMedium,
                                )

                                Text(
                                    text = product.nutrition.caloricBreakdown.percentFat.toString(),
                                    modifier = Modifier,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Normal,
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }

                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                color = MaterialTheme.colorScheme.surface
                            )

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                            ) {
                                Text(
                                    text = "Percentage of carbohydrates: ",
                                    modifier = Modifier,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.Normal,
                                    style = MaterialTheme.typography.titleMedium,
                                )

                                Text(
                                    text = "${product.nutrition.caloricBreakdown.percentCarbs}%",
                                    modifier = Modifier,
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Normal,
                                    style = MaterialTheme.typography.bodyMedium,
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.padding(8.dp))

                    Card(
                        modifier = Modifier,
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant,
                            contentColor = MaterialTheme.colorScheme.onBackground,
                        ),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                        ) {
                            Text(
                                text = "Ingredients:",
                                modifier = Modifier,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.SemiBold,
                                style = MaterialTheme.typography.titleMedium,
                            )

                            Text(
                                text = product.ingredientList,
                                modifier = Modifier,
                                color = MaterialTheme.colorScheme.onBackground,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Normal,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                }
            }
        }
    }
}
