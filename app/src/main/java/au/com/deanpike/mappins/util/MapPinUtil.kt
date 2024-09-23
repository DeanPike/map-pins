package au.com.deanpike.mappins.util

import au.com.deanpike.mappins.type.SoldIconType

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