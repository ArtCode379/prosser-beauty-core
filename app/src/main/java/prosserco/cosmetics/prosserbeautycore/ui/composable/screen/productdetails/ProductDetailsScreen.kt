package prosserco.cosmetics.prosserbeautycore.ui.composable.screen.productdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel
import prosserco.cosmetics.prosserbeautycore.data.model.Product
import prosserco.cosmetics.prosserbeautycore.ui.composable.shared.TSDIKContentWrapper
import prosserco.cosmetics.prosserbeautycore.ui.state.DataUiState
import prosserco.cosmetics.prosserbeautycore.ui.viewmodel.ProductDetailsViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel()
) {
    val state by viewModel.productDetailsState.collectAsState()
    LaunchedEffect(productId) { viewModel.observeProductDetails(productId) }
    TSDIKContentWrapper(
        dataState = state,
        dataPopulated = {
            ProductDetail((state as DataUiState.Populated).data, modifier, viewModel::addProductToCart)
        },
        dataEmpty = {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Product unavailable") }
        }
    )
}

@Composable
private fun ProductDetail(product: Product, modifier: Modifier, addToCart: () -> Unit) {
    var cartAdded by remember { mutableStateOf(false) }
    val pager = rememberPagerState(pageCount = { 3 })
    LaunchedEffect(cartAdded) {
        if (cartAdded) {
            delay(2000)
            cartAdded = false
        }
    }
    Box(modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(bottom = 92.dp)) {
            HorizontalPager(state = pager, modifier = Modifier.fillMaxWidth().height(340.dp)) {
                AsyncImage(product.imageUrl, product.title, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
            }
            Row(Modifier.fillMaxWidth().padding(top = 12.dp), horizontalArrangement = Arrangement.Center) {
                repeat(3) { index ->
                    Surface(
                        modifier = Modifier.padding(4.dp).height(7.dp).weight(0.03f),
                        shape = CircleShape,
                        color = if (index == pager.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline
                    ) {}
                }
            }
            Column(Modifier.padding(24.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Text(product.category.name, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.secondary)
                Text(product.title, style = MaterialTheme.typography.headlineMedium)
                Text(product.description, style = MaterialTheme.typography.bodyLarge, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Text("Thoughtfully selected for quality, texture and a beautiful place in your daily ritual.", style = MaterialTheme.typography.bodyMedium)
            }
        }
        Surface(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
            shadowElevation = 10.dp,
            color = MaterialTheme.colorScheme.surface
        ) {
            Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(18.dp)) {
                Text("£%.2f".format(product.price), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
                Button(
                    onClick = {
                        addToCart()
                        cartAdded = true
                    },
                    modifier = Modifier.weight(1f).height(52.dp)
                ) {
                    Text("Add to Cart")
                }
            }
        }
        AnimatedVisibility(
            visible = cartAdded,
            enter = slideInVertically { it },
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 84.dp)
        ) {
            Surface(color = MaterialTheme.colorScheme.surface, shadowElevation = 3.dp) {
                Row(Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Check, null, tint = MaterialTheme.colorScheme.primary)
                    Text("  Added to cart", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
