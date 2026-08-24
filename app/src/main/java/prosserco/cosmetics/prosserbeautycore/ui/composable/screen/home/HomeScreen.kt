package prosserco.cosmetics.prosserbeautycore.ui.composable.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import prosserco.cosmetics.prosserbeautycore.data.model.Product
import prosserco.cosmetics.prosserbeautycore.data.model.ProductCategory
import prosserco.cosmetics.prosserbeautycore.ui.composable.shared.TSDIKContentWrapper
import prosserco.cosmetics.prosserbeautycore.ui.state.DataUiState
import prosserco.cosmetics.prosserbeautycore.ui.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit
) {
    val state by viewModel.productsState.collectAsState()
    TSDIKContentWrapper(
        dataState = state,
        dataPopulated = {
            val products = (state as DataUiState.Populated).data
            ProductGallery(products, modifier, onNavigateToProductDetails)
        },
        dataEmpty = {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Our collection is being refreshed")
            }
        }
    )
}

@Composable
private fun ProductGallery(products: List<Product>, modifier: Modifier, onProduct: (Int) -> Unit) {
    var category by remember { mutableStateOf<ProductCategory?>(null) }
    val filtered = category?.let { chosen -> products.filter { it.category == chosen } } ?: products
    Column(modifier.fillMaxSize()) {
        Box(Modifier.fillMaxWidth().height(220.dp).clickable { onProduct(products.first().id) }) {
            AsyncImage(products.first().imageUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
            Column(Modifier.align(Alignment.BottomStart).fillMaxWidth().padding(20.dp)) {
                Text("THE BEAUTY EDIT", color = Color.White, style = MaterialTheme.typography.labelSmall)
                Text(products.first().title, color = Color.White, style = MaterialTheme.typography.headlineMedium)
            }
        }
        LazyRow(
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item { CategoryChip("All", category == null) { category = null } }
            items(ProductCategory.entries.size) { index ->
                val item = ProductCategory.entries[index]
                CategoryChip(item.name.lowercase().replaceFirstChar { it.uppercase() }, category == item) { category = item }
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            items(filtered, key = { it.id }) { product ->
                ProductCard(product, onProduct)
            }
        }
    }
}

@Composable
private fun CategoryChip(label: String, selected: Boolean, onClick: () -> Unit) {
    OutlinedCard(
        onClick = onClick,
        shape = RoundedCornerShape(50),
        border = BorderStroke(1.dp, if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline),
        colors = CardDefaults.outlinedCardColors(containerColor = Color.Transparent)
    ) {
        Text(label, modifier = Modifier.padding(horizontal = 16.dp, vertical = 9.dp), color = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface)
    }
}

@Composable
private fun ProductCard(product: Product, onProduct: (Int) -> Unit) {
    Card(
        onClick = { onProduct(product.id) },
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        AsyncImage(product.imageUrl, product.title, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxWidth().height(if (product.id % 2 == 0) 210.dp else 170.dp))
        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
            Text(product.category.name, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
            Text(product.title, style = MaterialTheme.typography.titleMedium)
            Text("£%.2f".format(product.price), style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.primary)
        }
    }
}
