package au.com.deanpike.mappins.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import au.com.deanpike.mappins.data.MapPinData
import au.com.deanpike.mappins.util.MapPinUtil
import com.google.android.gms.maps.model.LatLng

class MapScreenViewModel : ViewModel() {
    var uiState by mutableStateOf(
        StateData()
    )
        private set

    fun addMapPin(data: MapPinData) {
        val newPins = uiState.mapPins.toMutableMap()
        newPins[data.latLng] = data.copy(
            index = if (uiState.enableZIndex) MapPinUtil.getZIndex(data.pinType) else 0F
        )
        uiState = uiState.copy(
            mapPins = newPins
        )
    }

    fun pinClicked(latLng: LatLng) {
        val updatedPins = uiState.mapPins.toMutableMap()
        updatedPins.values.forEach { data ->
            val zIndex = if (uiState.enableZIndex) {
                MapPinUtil.getZIndex(data.pinType)
            } else {
                0F
            }
            if (data.latLng != latLng) {
                updatedPins[data.latLng] = data.copy(
                    currentlyViewing = false,
                    index = zIndex
                )
            } else {
                if (data.currentlyViewing) {
                    updatedPins[data.latLng] = data.copy(
                        currentlyViewing = false,
                        index = zIndex
                    )
                } else {
                    updatedPins[data.latLng] = data.copy(
                        currentlyViewing = true,
                        index = if (uiState.enableZIndex) 3F else 0F
                    )
                }
            }
        }
        uiState = uiState.copy(
            mapPins = updatedPins
        )
    }

    fun onClearClicked() {
        uiState = uiState.copy(
            mapPins = mutableMapOf()
        )
    }

    fun onEnableZIndex(it: Boolean) {
        uiState = uiState.copy(
            enableZIndex = it
        )
    }
}

data class StateData(
    val mapPins: MutableMap<LatLng, MapPinData> = mutableMapOf(),
    val enableZIndex: Boolean = false
)

