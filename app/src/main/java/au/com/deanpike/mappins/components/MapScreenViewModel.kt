package au.com.deanpike.mappins.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class MapScreenViewModel : ViewModel() {
    var uiState by mutableStateOf(
        StateData()
    )
        private set

    fun addMapPin(
        latLng: LatLng, data: MapPinData
    ) {
        val newPins = uiState.mapPins.toMutableMap()
        newPins[latLng] = data
        uiState = uiState.copy(
            mapPins = newPins
        )
    }
}

data class StateData(
    val mapPins: MutableMap<LatLng, MapPinData> = mutableMapOf()
)

