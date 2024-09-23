package au.com.deanpike.mappins.components.pins

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import au.com.deanpike.mappins.R
import au.com.deanpike.mappins.ui.theme.MappinsTheme
import au.com.deanpike.mappins.ui.theme.provider.LocalDomainColor
import au.com.domain.androiddesigntoken.typography.DomainTypography

@Composable
fun UnSoldMapPin(
    isShortListed: Boolean = false,
    isSeen: Boolean = false,
    markerCount: String = ""
) {
    Box(
        modifier = Modifier
            .padding(1.dp)
            .border(width = 1.dp, shape = RoundedCornerShape(8.dp), color = LocalDomainColor.current().neutralSurfaceDefault)
            .background(
                color = if (isShortListed) {
                    LocalDomainColor.current().neutralMediumDefault
                } else if (isSeen) {
                    LocalDomainColor.current().neutralBaseDefault
                } else {
                    LocalDomainColor.current().interactiveBaseDefault
                },
                shape = RoundedCornerShape(8.dp)
            )
            .defaultMinSize(16.dp, 16.dp)
    ) {
        val rowPadding = if (isShortListed) {
            if (markerCount.isEmpty()) {
                0.dp
            } else {
                6.dp
            }
        } else {
            if (markerCount.length > 2) {
                6.dp
            } else {
                4.dp
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = rowPadding, end = rowPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (isShortListed) {
                Icon(
                    modifier = Modifier
                        .height(11.dp)
                        .width(11.dp),
                    painter = painterResource(id = R.drawable.star_filled),
                    contentDescription = "Shortlisted",
                    tint = LocalDomainColor.current().accentFourTrimDefault,
                )
            }

            if (isShortListed && markerCount.isNotBlank()) {
                Spacer(modifier = Modifier.width(2.dp))
            }

            if (markerCount.isNotBlank()) {
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    text = markerCount,
                    style = DomainTypography.CompactMini.copy(
                        platformStyle = PlatformTextStyle(
                            includeFontPadding = false
                        ),
                    ),
                    color = LocalDomainColor.current().neutralSurfaceDefault,
                    textAlign = TextAlign.Start,
                )
            }
        }
    }
}

@Preview
@Composable
fun UnSoldMapPinPreview() {
    MappinsTheme {
        Column {
            UnSoldMapPin(
                isShortListed = false,
                isSeen = false,
                markerCount = "8"
            )
            UnSoldMapPin(
                isShortListed = true,
                isSeen = false,
                markerCount = "8"
            )
            UnSoldMapPin(
                isShortListed = false,
                isSeen = true,
                markerCount = "8"
            )
            UnSoldMapPin(
                isShortListed = true,
                isSeen = true,
                markerCount = "8"
            )
        }
    }
}