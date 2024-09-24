package au.com.deanpike.mappins.data

import au.com.deanpike.mappins.type.PinType
import com.google.android.gms.maps.model.LatLng

data class MapPinData(
    val pinType: PinType = PinType.UNSOLD_PIN,
    val latLng: LatLng = LatLng(0.0, 0.0),
    val count: String = "",
    val price: String = "",
    val isSeen: Boolean = false,
    val isShortListed: Boolean = false,
    val currentlyViewing: Boolean = false,
    val index: Float = 0F
)