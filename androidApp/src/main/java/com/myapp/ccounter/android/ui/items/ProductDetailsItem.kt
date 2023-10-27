package com.myapp.ccounter.android.ui.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.myapp.ccounter.domain.model.Product

@Composable
fun ProductDetailsItem(product: Product) {
    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Card(
            modifier = Modifier
                .height(220.dp)
                .width(220.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier,
            ) {
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center,
                ) {
                    AsyncImage(
                        model = product.image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
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
