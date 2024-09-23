package au.com.deanpike.mappins.components.pins

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.com.deanpike.mappins.R
import au.com.deanpike.mappins.type.SchoolCatchmentType
import au.com.deanpike.mappins.ui.theme.MappinsTheme
import au.com.deanpike.mappins.ui.theme.provider.LocalDomainColor
import au.com.deanpike.mappins.util.getSchoolPinColor

@Composable
fun SchoolMapPin(
    schoolType: SchoolCatchmentType
) {
    Box(
        modifier = Modifier
            .padding(1.dp)
            .border(width = 1.dp, shape = RoundedCornerShape(12.dp), color = LocalDomainColor.current().neutralSurfaceDefault)
            .background(color = getSchoolPinColor(schoolType), shape = RoundedCornerShape(12.dp))
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
}

@Preview
@Composable
fun PrimarySchoolPinPreview() {
    MappinsTheme() {
        SchoolMapPin(SchoolCatchmentType.PRIMARY)
    }
}

@Preview
@Composable
fun SecondarySchoolPinPreview() {
    MappinsTheme {
        SchoolMapPin(SchoolCatchmentType.SECONDARY)
    }
}

@Preview
@Composable
fun UnknownSchoolPinPreview() {
    MappinsTheme {
        SchoolMapPin(SchoolCatchmentType.UNKNOWN)
    }
}