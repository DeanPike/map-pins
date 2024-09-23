package au.com.deanpike.mappins.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import au.com.deanpike.mappins.ui.theme.provider.LocalDomainColor
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    innerPadding: PaddingValues
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

    GoogleMap(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = innerPadding,
        properties = mapProperties,
        cameraPositionState = cameraPositionState,
    ) {

    }

    Box(modifier = Modifier.padding(top = innerPadding.calculateTopPadding())) {
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(start = 16.dp, end = 16.dp)
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

enum class PinToCreateType {
    UNSOLD_PIN,
    UNSOLD_VIEWED_PIN,
    UNSOLD_SHORTLIST_PIN,
    SOLD_PIN,
    SOLD_SHORTLIST_PIN,
    PRIMARY_SCHOOL_PIN,
    SECONDARY_SCHOOL_PIN,
    UNKNOWN_SCHOOL_PIN
}