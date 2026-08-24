package prosserco.cosmetics.prosserbeautycore.ui.composable.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import prosserco.cosmetics.prosserbeautycore.ui.composable.shared.TSDIKContentWrapper
import prosserco.cosmetics.prosserbeautycore.ui.state.CartItemUiState
import prosserco.cosmetics.prosserbeautycore.ui.state.DataUiState
import prosserco.cosmetics.prosserbeautycore.ui.viewmodel.CartViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit
) {
    val state by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val total by viewModel.totalPrice.collectAsStateWithLifecycle()
    TSDIKContentWrapper(
        dataState = state,
        dataPopulated = {
            val items = (state as DataUiState.Populated).data
            CartContent(
                items = items,
                total = total,
                modifier = modifier,
                plus = viewModel::incrementProductInCart,
                minus = { item ->
                    if (item.quantity == 1) viewModel.deleteFromCart(item.productId) else viewModel.decrementItemInCart(item.productId)
                },
                remove = viewModel::deleteFromCart,
                checkout = onNavigateToCheckoutScreen
            )
        },
        dataEmpty = {
            Column(
                modifier = modifier.fillMaxSize().padding(32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Your beauty edit awaits", style = MaterialTheme.typography.headlineMedium)
                Text("Add a few favourites to begin your reservation.", modifier = Modifier.padding(vertical = 12.dp))
                OutlinedButton(onClick = { viewModel.incrementProductInCart(1) }) { Text("Start Shopping") }
            }
        }
    )
}

@Composable
private fun CartContent(
    items: List<CartItemUiState>,
    total: Double,
    modifier: Modifier,
    plus: (Int) -> Unit,
    minus: (CartItemUiState) -> Unit,
    remove: (Int) -> Unit,
    checkout: () -> Unit
) {
    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("Your selection", style = MaterialTheme.typography.headlineMedium)
        LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(items, key = { it.productId }) { item ->
                Card(shape = RoundedCornerShape(8.dp)) {
                    Row(Modifier.fillMaxWidth().padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        AsyncImage(item.productImageUrl, null, contentScale = ContentScale.Crop, modifier = Modifier.size(72.dp))
                        Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                            Text(item.productTitle, style = MaterialTheme.typography.titleMedium)
                            Text("£%.2f".format(item.productPrice), color = MaterialTheme.colorScheme.primary)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                OutlinedButton(onClick = { minus(item) }) { Text("−") }
                                Text("  ${item.quantity}  ")
                                OutlinedButton(onClick = { plus(item.productId) }) { Text("+") }
                            }
                        }
                        IconButton(onClick = { remove(item.productId) }) { Icon(Icons.Default.DeleteOutline, "Remove") }
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth().padding(vertical = 12.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Total", style = MaterialTheme.typography.titleLarge)
            Text("£%.2f".format(total), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
        }
        Button(onClick = checkout, modifier = Modifier.fillMaxWidth().height(54.dp)) { Text("Proceed to Checkout") }
    }
}
