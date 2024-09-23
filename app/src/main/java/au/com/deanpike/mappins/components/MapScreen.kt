package au.com.deanpike.mappins.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import au.com.deanpike.mappins.type.PinToCreateType
import au.com.deanpike.mappins.ui.theme.provider.LocalDomainColor
import au.com.deanpike.mappins.util.CreateMapIcon
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
        onPinAdded = { latLng, data ->
            viewModel.addMapPin(
                latLng = latLng,
                data = data
            )
        }
    )
}

@Composable
fun MapScreenContent(
    innerPadding: PaddingValues,
    state: StateData,
    onPinAdded: (LatLng, MapPinData) -> Unit
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
    var pinToCreate by remember {
        mutableStateOf(PinToCreateType.UNSOLD_PIN)
    }

    val mapMarkers = remember {
        mutableStateMapOf<LatLng, MapPinData>()
    }
    LaunchedEffect(state.mapPins) {
        mapMarkers.clear()
        state.mapPins.entries.forEach { entry ->
            println("---- Adding marker: $entry")
            mapMarkers[entry.key] = entry.value
        }
    }

    GoogleMap(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = innerPadding,
        properties = mapProperties,
        cameraPositionState = cameraPositionState,
        onMapLongClick = { latLng ->
            onPinAdded(
                latLng, MapPinData(
                    pinType = pinToCreate,
                    viewing = false
                )
            )
        }
    ) {
        mapMarkers.entries.forEach { entry ->
            MarkerComposable(
                keys = arrayOf(entry.key),
                state = MarkerState(position = entry.key),
                onClick = { marker ->
                    true
                }
            ) {
                CreateMapIcon(entry.value)
            }
        }
    }

    Box(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())) {
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                modifier = Modifier.wrapContentWidth(),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(width = 1.dp, color = LocalDomainColor.current().neutralSurfaceDefault),
                colors = ButtonDefaults.outlinedButtonColors().copy(
                    containerColor = LocalDomainColor.current().primaryBaseDefault
                ),
                onClick = { expandMenu = true }
            ) {
                Text(
                    text = "Create Icon",
                    style = MaterialTheme.typography.bodyMedium,
                    color = LocalDomainColor.current().neutralSurfaceDefault
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Row(
                modifier = Modifier
                    .weight(1F)
                    .height(32.dp)
                    .background(color = LocalDomainColor.current().neutralSurfaceDefault, shape = RoundedCornerShape(16.dp))
                    .padding(start = 4.dp, end = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = pinToCreate.description,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            if (expandMenu) {
                IconDropDown(
                    expandMenu = expandMenu,
                    onDismissed = {
                        expandMenu = false
                    },
                    onSelected = {
                        pinToCreate = it
                        expandMenu = false
                    }
                )
            }
        }
    }
}

@Composable
fun IconDropDown(
    expandMenu: Boolean,
    onDismissed: () -> Unit = {},
    onSelected: (PinToCreateType) -> Unit = {}
) {
    DropdownMenu(
        expanded = expandMenu,
        onDismissRequest = {
            onDismissed()
        }
    ) {
        DropdownMenuItem(
            text = {
                Text(
                    text = "Unsold pin",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            onClick = {
                onSelected(PinToCreateType.UNSOLD_PIN)
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = "Unsold viewed pin",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            onClick = {
                onSelected(PinToCreateType.UNSOLD_VIEWED_PIN)
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = "Unsold shortlisted pin",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            onClick = {
                onSelected(PinToCreateType.UNSOLD_SHORTLIST_PIN)
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = "Sold pin",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            onClick = {
                onSelected(PinToCreateType.SOLD_PIN)
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = "Sold shortlisted pin",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            onClick = {
                onSelected(PinToCreateType.SOLD_SHORTLIST_PIN)
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = "Primary school pin",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            onClick = {
                onSelected(PinToCreateType.PRIMARY_SCHOOL_PIN)
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = "Secondary school pin",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            onClick = {
                onSelected(PinToCreateType.SECONDARY_SCHOOL_PIN)
            }
        )
        DropdownMenuItem(
            text = {
                Text(
                    text = "Unknown school pin",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            onClick = {
                onSelected(PinToCreateType.UNKNOWN_SCHOOL_PIN)
            }
        )
    }
}

data class MapPinData(
    val pinType: PinToCreateType,
    val viewing: Boolean = false
)