package au.com.deanpike.mappins.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import au.com.deanpike.mappins.ui.theme.provider.*
import au.com.domain.androiddesigntoken.color.DomainColors
import au.com.domain.androiddesigntoken.color.DomainLightColors

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColorScheme(
    primary = DomainLightColors.primaryBaseDefault,
    onPrimary = DomainLightColors.primarySurfaceDefault,
    secondary = DomainLightColors.secondaryBaseDefault,
    onSecondary = DomainLightColors.secondarySurfaceDefault,
    /** for light grey background override this background setup
     * with NeutralSurfaceDefault in the component UI **/
    background = DomainLightColors.neutralBackgroundDefault,
    onBackground = DomainLightColors.neutralHeavyDefault,
    surface = DomainLightColors.neutralSurfaceDefault,
    onSurface = DomainLightColors.neutralHeavyDefault,
    error = DomainLightColors.criticalSubduedDefault,
    onError = DomainLightColors.criticalHeavyDefault
)

private val DomainRippleAlpha = RippleAlpha(0.25f, 0.25f, 0.25f, 0.25f)

@OptIn(ExperimentalMaterial3Api::class)
private val DomainRippleConfiguration = RippleConfiguration(color = DomainLightColors.interactiveBaseDefault, rippleAlpha = DomainRippleAlpha)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MappinsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val widthWidthSizeClass = DomainWindowWidthSizeClassProvider.get()
    val heightSizeClass = DomainWindowHeightSizeClassProvider.get()
    val isFoldable = DomainWindowWidthSizeClassProvider.isFoldable()

    MaterialTheme(
        colorScheme = LightColorPalette
    ) {
        CompositionLocalProvider(
            LocalRippleConfiguration provides DomainRippleConfiguration,
            LocalDomainDimension provides fun(): DomainDimension {
                return DomainDimensionByWindowWidthSize.get(windowWidthSizeClass = widthWidthSizeClass, isFoldable = isFoldable)
            },
            LocalDomainColor provides fun(): DomainColors {
                return DomainColorsByConfig.get(darkTheme = darkTheme)
            },
            LocalDomainWindowWidthSizeClass provides widthWidthSizeClass,
            LocalDomainWindowHeightSizeClass provides heightSizeClass,
            LocalDomainTypography provides fun(): DomainTypography {
                return DomainTypographyByWindowWidthSize.get(windowWidthSizeClass = widthWidthSizeClass, isFoldable = isFoldable)
            },
            content = content,
        )
    }
}