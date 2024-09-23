package au.com.deanpike.mappins.ui.theme.provider

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

enum class DomainWindowHeightSizeClass {
    COMPACT,
    MEDIUM,
    EXPANDED
}

object DomainWindowHeightSizeClassProvider {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    fun get(): DomainWindowHeightSizeClass {
        if (LocalInspectionMode.current) {
            return DpSize(
                width = LocalConfiguration.current.screenWidthDp.dp,
                height = LocalConfiguration.current.screenHeightDp.dp,
            ).mapToDomainWindowHeightSizeClass()
        }

        return LocalContext.current.findActivity()?.let {
            calculateWindowSizeClass(it).heightSizeClass.mapToDomainWindowHeightSizeClass()
        } ?: DomainWindowHeightSizeClass.COMPACT
    }

    private fun WindowHeightSizeClass.mapToDomainWindowHeightSizeClass(): DomainWindowHeightSizeClass {
        return when (this) {
            WindowHeightSizeClass.Compact -> DomainWindowHeightSizeClass.COMPACT
            WindowHeightSizeClass.Medium -> DomainWindowHeightSizeClass.MEDIUM
            else -> DomainWindowHeightSizeClass.EXPANDED
        }
    }

    // The sizes are taken from theis page https://developer.android.com/guide/topics/large-screens/support-different-screen-sizes#compose
    private fun DpSize.mapToDomainWindowHeightSizeClass(): DomainWindowHeightSizeClass {
        require(height >= 0.dp) { "Height must not be negative" }
        return when {
            height < 480.dp -> DomainWindowHeightSizeClass.COMPACT
            height < 900.dp -> DomainWindowHeightSizeClass.MEDIUM
            else -> DomainWindowHeightSizeClass.EXPANDED
        }
    }

    @Composable
    fun isCompactHeight() = get() == DomainWindowHeightSizeClass.COMPACT

    @Composable
    fun isMediumHeight() = get() == DomainWindowHeightSizeClass.MEDIUM

}

val LocalDomainWindowHeightSizeClass = staticCompositionLocalOf { DomainWindowHeightSizeClass.COMPACT }