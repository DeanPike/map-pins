package au.com.deanpike.mappins.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.GoogleMap

@Composable
fun MapScreen(
    innerPadding: PaddingValues
) {
    GoogleMap(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {

    }

}