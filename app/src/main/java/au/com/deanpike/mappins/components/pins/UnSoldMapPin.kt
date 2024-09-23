package au.com.deanpike.mappins.components.pins

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.com.deanpike.mappins.ui.theme.MapPinBorder
import au.com.deanpike.mappins.ui.theme.MappinsTheme
import au.com.deanpike.mappins.ui.theme.UnsoldMapPinBackground

@Composable
fun UnSoldMapPin(text: String) {
    Box(
        modifier = Modifier
            .background(
                color = UnsoldMapPinBackground,
                shape = RoundedCornerShape(8.dp)
            )
            .border(1.dp, MapPinBorder, RoundedCornerShape(8.dp))
            .defaultMinSize(minWidth = 16.dp, minHeight = 16.dp),
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
}

@Preview
@Composable
fun UnSoldMapPinPreview(){
    MappinsTheme {
        Row {
            UnSoldMapPin("8")
            UnSoldMapPin("88")
        }
    }
}