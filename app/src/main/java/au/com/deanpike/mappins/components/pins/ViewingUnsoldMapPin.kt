package au.com.deanpike.mappins.components.pins

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.com.deanpike.mappins.R
import au.com.deanpike.mappins.ui.theme.MapPinBorder
import au.com.deanpike.mappins.ui.theme.MappinsTheme
import au.com.deanpike.mappins.ui.theme.ViewingUnsoldMapPinBackground

@Composable
fun ViewingUnsoldMapPin(text: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = ViewingUnsoldMapPinBackground,
                    shape = RoundedCornerShape(12.dp)
                )
                .border(2.dp, MapPinBorder, RoundedCornerShape(12.dp))
                .defaultMinSize(minWidth = 24.dp, minHeight = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                text = text,
                color = Color.White,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Image(
            modifier = Modifier.offset(x = 0.dp, y = (-5.3).dp),
            painter = painterResource(id = R.drawable.viewing_unsold_map_pin_tip),
            contentDescription = "Pointer tip"
        )
    }
}

@Preview
@Composable
fun ViewingUnsoldMapPinPreview() {
    MappinsTheme {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround) {
            ViewingUnsoldMapPin("8")
            ViewingUnsoldMapPin("88")
            ViewingUnsoldMapPin("888")
            ViewingUnsoldMapPin("22")
        }
    }
}