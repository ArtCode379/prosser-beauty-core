package prosserco.cosmetics.prosserbeautycore.ui.composable.screen.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import prosserco.cosmetics.prosserbeautycore.R
import prosserco.cosmetics.prosserbeautycore.data.entity.OrderEntity
import prosserco.cosmetics.prosserbeautycore.ui.composable.shared.TSDIKContentWrapper
import prosserco.cosmetics.prosserbeautycore.ui.composable.shared.TSDIKEmptyView
import prosserco.cosmetics.prosserbeautycore.ui.state.DataUiState
import prosserco.cosmetics.prosserbeautycore.ui.viewmodel.OrderViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrderViewModel = koinViewModel(),
) {
    val ordersState by viewModel.ordersState.collectAsState()

    OrdersContent(
        ordersState = ordersState,
        modifier = modifier,
    )
}

@Composable
private fun OrdersContent(
    ordersState: DataUiState<List<OrderEntity>>,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {

        TSDIKContentWrapper(
            dataState = ordersState,

            dataPopulated = {
                val data = (ordersState as DataUiState.Populated).data

            },

            dataEmpty = {
                TSDIKEmptyView(
                    primaryText = stringResource(R.string.tsdik_orders_state_empty_primary_text),
                    modifier = Modifier.fillMaxSize(),
                )
            },
        )
    }
}