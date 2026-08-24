package prosserco.cosmetics.prosserbeautycore.ui.composable.screen.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import prosserco.cosmetics.prosserbeautycore.R
import prosserco.cosmetics.prosserbeautycore.ui.viewmodel.TSDIKOnboardingVM

private data class Page(@DrawableRes val image: Int, val title: String, val copy: String)

private val pages = listOf(
    Page(R.drawable.onboarding_1, "A ritual made for you", "Explore considered skincare and beauty essentials selected to elevate every day."),
    Page(R.drawable.onboarding_2, "Discover your signature", "Move from petal-soft colour to expressive fragrance with formulas chosen for pleasure and performance."),
    Page(R.drawable.onboarding_3, "Reserve with confidence", "Build your basket, reserve in moments, and collect your order from our store within 24 hours.")
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: TSDIKOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit
) {
    val completed by viewModel.onboardingSetState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { pages.size })
    LaunchedEffect(completed) { if (completed) onNavigateToHomeScreen() }
    Box(modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { index ->
            val page = pages[index]
            Box(Modifier.fillMaxSize()) {
                Image(
                    painter = androidx.compose.ui.res.painterResource(page.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize().padding(top = 210.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface.copy(alpha = 0.94f)).padding(28.dp)
                ) {
                    Text("PROSSER BEAUTY", style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.primary)
                    Text(page.title, style = MaterialTheme.typography.displayLarge)
                    Text(page.copy, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                pages.indices.forEach { index ->
                    Box(Modifier.size(if (index == pagerState.currentPage) 10.dp else 7.dp).background(
                        if (index == pagerState.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface,
                        CircleShape
                    ))
                }
            }
            if (pagerState.currentPage == pages.lastIndex) {
                Button(onClick = viewModel::setOnboarded, modifier = Modifier.fillMaxWidth().height(54.dp)) {
                    Text(stringResource(R.string.tsdik_start_button_title))
                }
            }
        }
    }
}
