package au.com.deanpike.mappins.ui.theme.provider

import au.com.domain.androiddesigntoken.typography.DomainTypography as DesignTokenTypography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle

interface DomainTypography {
    val body: TextStyle
    val bodyBold: TextStyle
    val caption: TextStyle
    val captionBold: TextStyle
    val displayLarge: TextStyle
    val displayMedium: TextStyle
    val displaySmall: TextStyle
    val heading: TextStyle
    val lead: TextStyle
    val mini: TextStyle
    val miniBold: TextStyle
    val subheading: TextStyle
    val overline: TextStyle
    val largeMini: TextStyle
    val largeCaption: TextStyle
    val pageHeading: TextStyle
}

object DomainTypographyByWindowWidthSize {

    private val compactTypography = object : DomainTypography {
        override val body: TextStyle = DesignTokenTypography.CompactBody
        override val bodyBold: TextStyle = DesignTokenTypography.CompactBodyBold
        override val caption: TextStyle = DesignTokenTypography.CompactCaption
        override val captionBold: TextStyle = DesignTokenTypography.CompactCaptionBold
        override val displayLarge: TextStyle = DesignTokenTypography.CompactDisplayLarge
        override val displayMedium: TextStyle = DesignTokenTypography.CompactDisplayMedium
        override val displaySmall: TextStyle = DesignTokenTypography.CompactDisplaySmall
        override val heading: TextStyle = DesignTokenTypography.CompactHeading
        override val lead: TextStyle = DesignTokenTypography.CompactLead
        override val mini: TextStyle = DesignTokenTypography.CompactMini
        override val miniBold: TextStyle = DesignTokenTypography.CompactMiniBold
        override val subheading: TextStyle = DesignTokenTypography.CompactSubheading
        override val overline: TextStyle = DesignTokenTypography.CompactOverline
        override val largeMini: TextStyle = DesignTokenTypography.LargeMini
        override val largeCaption: TextStyle = DesignTokenTypography.LargeCaption
        override val pageHeading: TextStyle = DesignTokenTypography.CompactPageheading
    }

    private val regularTypography = object : DomainTypography {
        override val body: TextStyle = DesignTokenTypography.RegularBody
        override val bodyBold: TextStyle = DesignTokenTypography.RegularBodyBold
        override val caption: TextStyle = DesignTokenTypography.RegularCaption
        override val captionBold: TextStyle = DesignTokenTypography.RegularCaptionBold
        override val displayLarge: TextStyle = DesignTokenTypography.RegularDisplayLarge
        override val displayMedium: TextStyle = DesignTokenTypography.RegularDisplayMedium
        override val displaySmall: TextStyle = DesignTokenTypography.RegularDisplaySmall
        override val heading: TextStyle = DesignTokenTypography.RegularHeading
        override val lead: TextStyle = DesignTokenTypography.RegularLead
        override val mini: TextStyle = DesignTokenTypography.RegularMini
        override val miniBold: TextStyle = DesignTokenTypography.RegularMiniBold
        override val subheading: TextStyle = DesignTokenTypography.RegularSubheading
        override val overline: TextStyle = DesignTokenTypography.RegularOverline
        override val largeMini: TextStyle = DesignTokenTypography.LargeMini
        override val largeCaption: TextStyle = DesignTokenTypography.LargeCaption
        override val pageHeading: TextStyle = DesignTokenTypography.RegularPageheading
    }

    fun get(
        windowWidthSizeClass: DomainWindowWidthSizeClass,
        isFoldable: Boolean
    ): DomainTypography {
        return when (windowWidthSizeClass) {
            DomainWindowWidthSizeClass.COMPACT -> compactTypography
            DomainWindowWidthSizeClass.MEDIUM -> if (isFoldable) compactTypography else regularTypography
            else -> regularTypography
        }
    }

}

val LocalDomainTypography = staticCompositionLocalOf<() -> DomainTypography> {
    error("CompositionLocal LocalDomainDimension not present")
}