package au.com.deanpike.mappins.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.com.deanpike.mappins.R
import au.com.deanpike.mappins.data.MapPinData
import au.com.deanpike.mappins.type.PinType
import au.com.deanpike.mappins.ui.theme.MappinsTheme
import au.com.deanpike.mappins.ui.theme.provider.LocalDomainColor

@Composable
fun BottomSheetContent(
    data: MapPinData,
    onApply: (MapPinData) -> Unit = {}
) {
    var expandMenu by remember {
        mutableStateOf(false)
    }
    var selectedPinType by remember {
        mutableStateOf(data.pinType)
    }
    var count by remember {
        mutableStateOf(data.count)
    }
    var price by remember {
        mutableStateOf(data.price)
    }
    var seen by remember {
        mutableStateOf(data.isSeen)
    }
    var shortListed by remember {
        mutableStateOf(data.isShortListed)
    }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = LocalDomainColor.current().neutralSurfaceDefault, shape = RoundedCornerShape(8.dp))
            .padding(16.dp)
            .scrollable(state = scrollState, orientation = Orientation.Vertical),
    ) {
        Row {
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    expandMenu = true
                }
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(R.drawable.chevron_down_outline),
                        contentDescription = "Down arrow"
                    )
                    Spacer(Modifier.weight(1F))
                    Text(text = selectedPinType.description)
                    Spacer(Modifier.weight(1F))
                    Icon(
                        painter = painterResource(R.drawable.chevron_down_outline),
                        contentDescription = "Down arrow"
                    )
                }
            }
            DropdownMenu(
                expanded = expandMenu,
                onDismissRequest = {
                    expandMenu = false
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
                        selectedPinType = PinType.UNSOLD_PIN
                        expandMenu = false
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
                        selectedPinType = PinType.SOLD_PIN
                        expandMenu = false
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
                        selectedPinType = PinType.PRIMARY_SCHOOL_PIN
                        expandMenu = false
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
                        selectedPinType = PinType.SECONDARY_SCHOOL_PIN
                        expandMenu = false
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
                        selectedPinType = PinType.UNKNOWN_SCHOOL_PIN
                        expandMenu = false
                    }
                )
            }
        }

        if (selectedPinType == PinType.SOLD_PIN || selectedPinType == PinType.UNSOLD_PIN) {
            Row(modifier = Modifier.fillMaxWidth()) {
                if (selectedPinType == PinType.UNSOLD_PIN) {
                    Row(
                        modifier = Modifier.weight(1F),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Seen")
                        Checkbox(
                            checked = seen,
                            onCheckedChange = {
                                seen = !seen
                            }
                        )
                    }
                }
                Row(
                    modifier = Modifier.weight(1F),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Shortlisted")
                    Checkbox(
                        checked = shortListed,
                        onCheckedChange = {
                            shortListed = !shortListed
                        }
                    )
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            if (selectedPinType == PinType.UNSOLD_PIN) {
                TextField(
                    modifier = Modifier.weight(0.5F),
                    colors = TextFieldDefaults.colors().copy(
                        focusedContainerColor = LocalDomainColor.current().neutralSurfaceDefault,
                        unfocusedContainerColor = LocalDomainColor.current().neutralSurfaceDefault,
                    ),
                    label = {
                        Text(text = "Count")
                    },
                    value = count,
                    onValueChange = {
                        count = it
                    }
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            if (selectedPinType == PinType.SOLD_PIN) {
                TextField(
                    modifier = Modifier.weight(0.5F),
                    colors = TextFieldDefaults.colors().copy(
                        focusedContainerColor = LocalDomainColor.current().neutralSurfaceDefault,
                        unfocusedContainerColor = LocalDomainColor.current().neutralSurfaceDefault,
                    ),
                    label = {
                        Text(text = "Price")
                    },
                    value = price,
                    onValueChange = {
                        price = it
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onApply(
                    MapPinData(
                        pinType = selectedPinType,
                        latLng = data.latLng,
                        count = count,
                        price = price,
                        isSeen = seen,
                        isShortListed = shortListed,
                        index = when (selectedPinType) {
                            PinType.UNSOLD_PIN -> 2F
                            PinType.SOLD_PIN -> 0F
                            PinType.PRIMARY_SCHOOL_PIN, PinType.SECONDARY_SCHOOL_PIN, PinType.UNKNOWN_SCHOOL_PIN -> 1F
                        }
                    )
                )
            }
        ) {
            Text(text = "Apply")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview() {
    MappinsTheme {
        BottomSheetContent(
            data = MapPinData(
                pinType = PinType.UNSOLD_PIN,
            )
        )
    }
}