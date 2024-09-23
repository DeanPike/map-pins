package au.com.deanpike.mappins.ui.theme.provider

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import au.com.domain.androiddesigntoken.dimensions.DomainCompactDimensions
import au.com.domain.androiddesigntoken.dimensions.DomainRegularDimensions

interface DomainDimension {
    val none: Dp
    val xxs: Dp
    val xs: Dp
    val s: Dp
    val m: Dp
    val l: Dp
    val xl: Dp
    val xxl: Dp
}

object DomainDimensionByWindowWidthSize {

    private val compactDimension = object: DomainDimension {
        override val none: Dp = DomainCompactDimensions.None
        override val xxs: Dp = DomainCompactDimensions.Xxs
        override val xs: Dp = DomainCompactDimensions.Xs
        override val s: Dp = DomainCompactDimensions.S
        override val m: Dp = DomainCompactDimensions.M
        override val l: Dp = DomainCompactDimensions.L
        override val xl: Dp = DomainCompactDimensions.Xl
        override val xxl: Dp = DomainCompactDimensions.Xxl
    }

    private val regularDimension = object: DomainDimension {
        override val none: Dp = DomainRegularDimensions.None
        override val xxs: Dp = DomainRegularDimensions.Xxs
        override val xs: Dp = DomainRegularDimensions.Xs
        override val s: Dp = DomainRegularDimensions.S
        override val m: Dp = DomainRegularDimensions.M
        override val l: Dp = DomainRegularDimensions.L
        override val xl: Dp = DomainRegularDimensions.Xl
        override val xxl: Dp = DomainRegularDimensions.Xxl
    }

    fun get(
        windowWidthSizeClass: DomainWindowWidthSizeClass,
        isFoldable: Boolean
    ): DomainDimension {
        return when (windowWidthSizeClass) {
            DomainWindowWidthSizeClass.COMPACT -> compactDimension
            DomainWindowWidthSizeClass.MEDIUM -> if (isFoldable) compactDimension else regularDimension
            else -> regularDimension
        }
    }
}

val LocalDomainDimension = staticCompositionLocalOf<() -> DomainDimension> {
    error("CompositionLocal LocalDomainDimension not present")
}