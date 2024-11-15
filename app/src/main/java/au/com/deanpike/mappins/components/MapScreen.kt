package au.com.deanpike.mappins.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import au.com.deanpike.mappins.data.MapPinData
import au.com.deanpike.mappins.ui.theme.provider.LocalDomainColor
import au.com.deanpike.mappins.util.CreateMapIcon
import au.com.deanpike.mappins.viewmodel.MapScreenViewModel
import au.com.deanpike.mappins.viewmodel.StateData
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MarkerComposable
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    innerPadding: PaddingValues,
    viewModel: MapScreenViewModel = viewModel()
) {

    MapScreenContent(
        innerPadding = innerPadding,
        state = viewModel.uiState,
        onPinAdded = { data ->
            viewModel.addMapPin(
                data = data
            )
        },
        onPinClicked = { latLng ->
            viewModel.pinClicked(latLng)

        },
        onClearClicked = {
            viewModel.onClearClicked()
        },
        onEnableZIndex = {
            viewModel.onEnableZIndex(it)
        }
    )
}

@Composable
fun MapScreenContent(
    innerPadding: PaddingValues,
    state: StateData,
    onPinAdded: (MapPinData) -> Unit,
    onPinClicked: (LatLng) -> Unit,
    onClearClicked: () -> Unit,
    onEnableZIndex: (Boolean) -> Unit
) {
    val cameraPositionState: CameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-33.879002, 151.186359), 15.0F)
    }
    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoomPreference = 20f,
                minZoomPreference = 2f,
                mapType = MapType.NORMAL
            )
        )
    }
    var expandMenu by remember {
        mutableStateOf(false)
    }
    var localLatLng by remember {
        mutableStateOf(LatLng(0.0, 0.0))
    }
    val mapMarkers = rememberUpdatedState(state.mapPins)

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        contentPadding = innerPadding,
        properties = mapProperties,
        cameraPositionState = cameraPositionState,
        onMapLongClick = { latLng ->
            localLatLng = latLng
            expandMenu = true
        },
    ) {
        mapMarkers.value.entries.forEach { entry ->
            MarkerComposable(
                keys = arrayOf(entry.key, entry.value),
                state = MarkerState(position = entry.key),
                onClick = { marker ->
                    onPinClicked(marker.position)
                    true
                },
                zIndex = entry.value.index
            ) {
                CreateMapIcon(entry.value)
            }
        }
    }

    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 6.dp, top = 32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    onClearClicked()
                }
            ) {
                Text(text = "Clear")
            }
            Spacer(Modifier.weight(1F))
            Row(
                modifier = Modifier
                    .background(color = LocalDomainColor.current().neutralSurfaceDefault, shape = RoundedCornerShape(8.dp))
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Enable z-index")
                Checkbox(
                    checked = state.enableZIndex,
                    onCheckedChange = {
                        onEnableZIndex(it)
                    }
                )
            }
        }
    }

    if (expandMenu) {
        Dialog(
            onDismissRequest = {
                expandMenu = false
            }
        ) {
            BottomSheetContent(
                data = MapPinData(
                    latLng = localLatLng
                ),
                onApply = { data ->
                    expandMenu = false
                    onPinAdded(data)
                }
            )
        }
    }
}