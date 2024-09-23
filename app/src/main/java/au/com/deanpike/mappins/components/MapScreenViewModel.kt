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

    fun addMapPin(data: MapPinData) {
        val newPins = uiState.mapPins.toMutableMap()
        newPins[data.latLng] = data
        uiState = uiState.copy(
            mapPins = newPins
        )
    }

    fun pinClicked(latLng: LatLng) {
        val updatedPins = uiState.mapPins.toMutableMap()
        val data = updatedPins[latLng]
        data?.let {
            updatedPins[latLng] = updatedPins[latLng]!!.copy(
                currentlyViewing = !data.currentlyViewing
            )
            uiState = uiState.copy(
                mapPins = updatedPins
            )
        }

    }
}

data class StateData(
    val mapPins: MutableMap<LatLng, MapPinData> = mutableMapOf()
)

