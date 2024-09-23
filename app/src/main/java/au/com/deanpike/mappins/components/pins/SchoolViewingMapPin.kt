package au.com.deanpike.mappins.components.pins

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import au.com.deanpike.mappins.R
import au.com.deanpike.mappins.type.SchoolCatchmentType
import au.com.deanpike.mappins.ui.theme.MappinsTheme
import au.com.deanpike.mappins.ui.theme.provider.LocalDomainColor
import au.com.deanpike.mappins.util.getSchoolPinColorViewing
import au.com.deanpike.mappins.util.getSchoolPointer

@Composable
fun SchoolViewingMapPin(schoolType: SchoolCatchmentType) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(1.dp)
                .border(width = 2.dp, shape = RoundedCornerShape(12.dp), color = LocalDomainColor.current().neutralSurfaceDefault)
                .background(color = getSchoolPinColorViewing(schoolType), shape = RoundedCornerShape(12.dp))
                .defaultMinSize(24.dp, 24.dp)
        ) {

            Row(
                modifier = Modifier.align(Alignment.Center),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .height(12.dp)
                        .width(12.dp),
                    painter = painterResource(id = R.drawable.school_outline),
                    contentDescription = "Shortlisted",
                    tint = LocalDomainColor.current().neutralSurfaceDefault,
                )
            }
        }

        Image(
            modifier = Modifier.offset {
                IntOffset(x = 0, y = with(this) { -7.dp.roundToPx() })
            },
            painter = painterResource(id = getSchoolPointer(schoolType)),
            contentDescription = "unseen viewing pin tip"
        )
    }
}

@Preview
@Composable
fun ViewingPrimarySchoolPinPreview() {
    MappinsTheme {
        SchoolViewingMapPin(
            schoolType = SchoolCatchmentType.PRIMARY
        )
    }
}

@Preview
@Composable
fun ViewingSecondarySchoolPinPreview() {
    MappinsTheme {
        SchoolViewingMapPin(
            schoolType = SchoolCatchmentType.SECONDARY
        )
    }
}

@Preview
@Composable
fun ViewingUnknownSchoolPinPreview() {
    MappinsTheme {
        SchoolViewingMapPin(
            schoolType = SchoolCatchmentType.UNKNOWN
        )
    }
}