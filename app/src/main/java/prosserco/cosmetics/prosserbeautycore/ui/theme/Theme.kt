package prosserco.cosmetics.prosserbeautycore.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val BrandColors = lightColorScheme(
    primary = Primary,
    onPrimary = OnPrimary,
    secondary = Accent,
    background = Background,
    surface = Surface,
    onSurface = OnSurface,
    onBackground = OnSurface,
    outline = Border,
    surfaceVariant = ChipBackground,
    onSurfaceVariant = Muted
)

@Composable
fun ProductAppTSDIKTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(colorScheme = BrandColors, typography = AppTypography, content = content)
}
