package au.com.deanpike.mappins.ui.theme.provider

import androidx.compose.runtime.staticCompositionLocalOf
import au.com.domain.androiddesigntoken.color.DomainColors
import au.com.domain.androiddesigntoken.color.DomainLightColors

object DomainColorsByConfig {

    val light = DomainLightColors

    val dark = light

    fun get(darkTheme: Boolean): DomainColors {
        return if (darkTheme) dark else light
    }
}

val LocalDomainColor = staticCompositionLocalOf<() -> DomainColors> {
    error("CompositionLocal LocalDomainColor not present")
}