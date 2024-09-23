package au.com.deanpike.mappins.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import au.com.deanpike.mappins.R
import au.com.deanpike.mappins.components.MapPinData
import au.com.deanpike.mappins.components.pins.SchoolMapPin
import au.com.deanpike.mappins.components.pins.SoldMapPin
import au.com.deanpike.mappins.components.pins.UnSoldMapPin
import au.com.deanpike.mappins.type.PinToCreateType
import au.com.deanpike.mappins.type.SchoolCatchmentType
import au.com.deanpike.mappins.type.SoldIconType
import au.com.deanpike.mappins.ui.theme.provider.LocalDomainColor

object MapPinUtil {

    fun getSoldPinText(
        markerCount: String = "",
        markerPrice: String = "",
        iconType: SoldIconType = SoldIconType.FURLED,
    ): String {
        return markerCount.ifBlank {
            if (iconType == SoldIconType.FURLED) {
                if (markerPrice.isNotBlank()) {
                    "$"
                } else {
                    ""
                }
            } else {
                markerPrice
            }
        }
    }
}

@Composable
fun getSchoolPinColor(schoolType: SchoolCatchmentType?): Color {
    return when (schoolType) {
        SchoolCatchmentType.PRIMARY -> LocalDomainColor.current().accentOneBaseDefault
        SchoolCatchmentType.SECONDARY -> LocalDomainColor.current().accentFourBaseDefault
        SchoolCatchmentType.UNKNOWN -> LocalDomainColor.current().accentTwoBaseDefault
        else -> LocalDomainColor.current().accentTwoBaseDefault
    }
}

@Composable
fun getSchoolPinColorViewing(schoolType: SchoolCatchmentType?): Color {
    return when (schoolType) {
        SchoolCatchmentType.PRIMARY -> LocalDomainColor.current().accentOneBasePressed
        SchoolCatchmentType.SECONDARY -> LocalDomainColor.current().accentFourBasePressed
        SchoolCatchmentType.UNKNOWN -> LocalDomainColor.current().accentTwoBasePressed
        else -> LocalDomainColor.current().accentTwoBasePressed
    }
}

@Composable
fun getSchoolPointer(schoolType: SchoolCatchmentType?): Int {
    return when (schoolType) {
        SchoolCatchmentType.PRIMARY -> R.drawable.primary_school_tip
        SchoolCatchmentType.SECONDARY -> R.drawable.secondary_school_tip
        SchoolCatchmentType.UNKNOWN -> R.drawable.unknown_school_tip
        else -> R.drawable.unknown_school_tip
    }
}

@Composable
fun CreateMapIcon(
    data: MapPinData
) {
    when (data.pinType) {
        PinToCreateType.UNSOLD_PIN -> UnSoldMapPin()
        PinToCreateType.UNSOLD_PIN_WITH_COUNT -> UnSoldMapPin(markerCount = "8")
        PinToCreateType.UNSOLD_VIEWED_PIN -> UnSoldMapPin(isViewed = true)
        PinToCreateType.UNSOLD_SHORTLIST_PIN -> UnSoldMapPin(isShortListed = true)
        PinToCreateType.SOLD_PIN -> SoldMapPin(markerPrice = "$1.2m")
        PinToCreateType.SOLD_SHORTLIST_PIN -> SoldMapPin(isShortListed = true, markerPrice = "$1.2m")
        PinToCreateType.PRIMARY_SCHOOL_PIN -> SchoolMapPin(schoolType = SchoolCatchmentType.PRIMARY)
        PinToCreateType.SECONDARY_SCHOOL_PIN -> SchoolMapPin(schoolType = SchoolCatchmentType.SECONDARY)
        PinToCreateType.UNKNOWN_SCHOOL_PIN -> SchoolMapPin(schoolType = SchoolCatchmentType.UNKNOWN)
    }
}
