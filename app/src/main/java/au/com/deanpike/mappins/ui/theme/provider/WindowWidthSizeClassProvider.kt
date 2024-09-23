package au.com.deanpike.mappins.ui.theme.provider

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import androidx.window.layout.WindowLayoutInfo

enum class DomainWindowWidthSizeClass {
    COMPACT,
    MEDIUM,
    EXPANDED
}

object DomainWindowWidthSizeClassProvider {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    fun get(): DomainWindowWidthSizeClass {
        if (LocalInspectionMode.current) {
            return DpSize(
                width = LocalConfiguration.current.screenWidthDp.dp,
                height = LocalConfiguration.current.screenHeightDp.dp,
            ).mapToDomainWindowWidthSizeClass()
        }

        return LocalContext.current.findActivity()?.let {
            calculateWindowSizeClass(it).widthSizeClass.mapToDomainWindowWidthSizeClass()
        } ?: DomainWindowWidthSizeClass.COMPACT
    }

    private fun WindowWidthSizeClass.mapToDomainWindowWidthSizeClass(): DomainWindowWidthSizeClass? {
        return when (this) {
            WindowWidthSizeClass.Compact -> DomainWindowWidthSizeClass.COMPACT
            WindowWidthSizeClass.Medium -> DomainWindowWidthSizeClass.MEDIUM
            WindowWidthSizeClass.Expanded -> DomainWindowWidthSizeClass.EXPANDED
            else -> null
        }
    }

    // align with the internal function: androidx.compose.material3.windowsizeclass.WindowWidthSizeClass.fromWidth
    private fun DpSize.mapToDomainWindowWidthSizeClass(): DomainWindowWidthSizeClass {
        require(width >= 0.dp) { "Width must not be negative" }
        return when {
            width < 600.dp -> DomainWindowWidthSizeClass.COMPACT
            width < 840.dp -> DomainWindowWidthSizeClass.MEDIUM
            else -> DomainWindowWidthSizeClass.EXPANDED
        }
    }

    @Composable
    fun isCompactWidth() = get() == DomainWindowWidthSizeClass.COMPACT

    @Composable
    fun isExpandedWidth() = get() == DomainWindowWidthSizeClass.EXPANDED

    @Composable
    fun isFoldable(): Boolean {
        return LocalContext.current.findActivity()?.let {
            val layoutInfo by WindowInfoTracker.getOrCreate(it).windowLayoutInfo(it).collectAsState(initial = null)
            layoutInfo?.mapToDisplayFeatures()
        } ?: false
    }

    private fun WindowLayoutInfo.mapToDisplayFeatures(): Boolean {
        // checker to see if device has folding feature (e.g. HINGE)
        return this.displayFeatures.isNotEmpty() && ((this.displayFeatures[0] is FoldingFeature))
    }
}

val LocalDomainWindowWidthSizeClass = staticCompositionLocalOf { DomainWindowWidthSizeClass.COMPACT }

/**
 * Find the closest Activity in a given Context.
 * https://github.com/google/accompanist/blob/6611ebda55eb2948eca9e1c89c2519e80300855a/permissions/src/main/java/com/google/accompanist/permissions/PermissionsUtil.kt#L99
 */
fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    return null
}