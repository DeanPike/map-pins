package au.com.deanpike.mappins.components.pins

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import au.com.deanpike.mappins.R
import au.com.deanpike.mappins.type.SoldIconType
import au.com.deanpike.mappins.ui.theme.MappinsTheme
import au.com.deanpike.mappins.ui.theme.provider.LocalDomainColor
import au.com.deanpike.mappins.util.MapPinUtil.getSoldPinText
import au.com.domain.androiddesigntoken.typography.DomainTypography

@Composable
fun SoldViewingMapPin(
    isShortListed: Boolean = false,
    markerCount: String = "",
    markerPrice: String = "",
    iconType: SoldIconType = SoldIconType.FURLED,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(1.dp)
                .border(width = 2.dp, shape = RoundedCornerShape(4.dp), color = LocalDomainColor.current().neutralSurfaceDefault)
                .background(color = if (isShortListed) LocalDomainColor.current().accentFiveBaseSelected else LocalDomainColor.current().accentFiveBasePressed, shape = RoundedCornerShape(4.dp))
                .defaultMinSize(24.dp, 24.dp)

        ) {
            val markerText = getSoldPinText(
                markerCount = markerCount,
                markerPrice = markerPrice,
                iconType = iconType
            )
            val rowPadding = if (isShortListed) {
                if (markerText.isEmpty()) {
                    0.dp
                } else {
                    6.dp
                }
            } else {
                if (markerText.length > 2) {
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

                if (isShortListed && markerText.isNotBlank()) {
                    Spacer(modifier = Modifier.width(2.dp))
                }

                if (markerText.isNotBlank()) {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterVertically),
                        text = markerText,
                        style = DomainTypography.CompactMini,
                        color = LocalDomainColor.current().neutralSurfaceDefault,
                        textAlign = TextAlign.Start,
                    )
                }
            }
        }
        Image(
            modifier = Modifier.offset {
                IntOffset(x = 0, y = with(this) { -7.dp.roundToPx() })
            },
            painter = painterResource(id = if (isShortListed) R.drawable.sold_shortlisted_viewing_tip else R.drawable.sold_viewing_tip),
            contentDescription = "unseen viewing pin tip"
        )
    }
}

@Preview
@Composable
fun ViewingSoldPinFurledPreview() {
    MappinsTheme() {
        Column {
            SoldViewingMapPin(
                isShortListed = false,
                markerCount = "",
                markerPrice = "",
                iconType = SoldIconType.FURLED
            )
            SoldViewingMapPin(
                isShortListed = false,
                markerCount = "8",
                markerPrice = "",
                iconType = SoldIconType.FURLED
            )
            SoldViewingMapPin(
                isShortListed = false,
                markerCount = "",
                markerPrice = "$1.2m",
                iconType = SoldIconType.FURLED
            )

            SoldViewingMapPin(
                isShortListed = true,
                markerCount = "",
                markerPrice = "",
                iconType = SoldIconType.FURLED
            )
            SoldViewingMapPin(
                isShortListed = true,
                markerCount = "8",
                markerPrice = "",
                iconType = SoldIconType.FURLED
            )
            SoldViewingMapPin(
                isShortListed = true,
                markerCount = "",
                markerPrice = "$1.2m",
                iconType = SoldIconType.FURLED
            )
        }
    }
}

@Preview
@Composable
fun ViewingSoldPinUnFurledPreview() {
    MappinsTheme() {
        Column {
            SoldViewingMapPin(
                isShortListed = false,
                markerCount = "",
                markerPrice = "",
                iconType = SoldIconType.UNFURLED
            )
            SoldViewingMapPin(
                isShortListed = false,
                markerCount = "8",
                markerPrice = "",
                iconType = SoldIconType.UNFURLED
            )
            SoldViewingMapPin(
                isShortListed = false,
                markerCount = "",
                markerPrice = "$1.2m",
                iconType = SoldIconType.UNFURLED
            )

            SoldViewingMapPin(
                isShortListed = true,
                markerCount = "",
                markerPrice = "",
                iconType = SoldIconType.UNFURLED
            )
            SoldViewingMapPin(
                isShortListed = true,
                markerCount = "8",
                markerPrice = "",
                iconType = SoldIconType.UNFURLED
            )
            SoldViewingMapPin(
                isShortListed = true,
                markerCount = "",
                markerPrice = "$1.2m",
                iconType = SoldIconType.UNFURLED
            )
        }
    }
}