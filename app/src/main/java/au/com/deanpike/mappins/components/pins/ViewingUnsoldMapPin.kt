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
import au.com.deanpike.mappins.ui.theme.MappinsTheme
import au.com.deanpike.mappins.ui.theme.provider.LocalDomainColor
import au.com.domain.androiddesigntoken.typography.DomainTypography

@Composable
fun ViewingUnsoldMapPin(
    isShortListed: Boolean = false,
    markerCount: String = ""
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .padding(1.dp)
                .border(width = 2.dp, shape = RoundedCornerShape(12.dp), color = LocalDomainColor.current().neutralSurfaceDefault)
                .background(
                    color = if (isShortListed) LocalDomainColor.current().interactiveBaseSelected else LocalDomainColor.current().interactiveBasePressed,
                    shape = RoundedCornerShape(12.dp)
                )
                .defaultMinSize(24.dp, 24.dp)

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
            painter = painterResource(id = if (isShortListed) R.drawable.unsold_shortlisted_viewing_tip else R.drawable.unsold_viewing_tip),
            contentDescription = "unseen viewing pin tip"
        )
    }
}

@Preview
@Composable
fun ViewingUnsoldMapPinPreview() {
    MappinsTheme {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            ViewingUnsoldMapPin(
                isShortListed = false,
                markerCount = "8"
            )
            ViewingUnsoldMapPin(
                isShortListed = true,
                markerCount = "8"
            )
            ViewingUnsoldMapPin(
                isShortListed = false,
                markerCount = "88"
            )
            ViewingUnsoldMapPin(
                isShortListed = true,
                markerCount = "88"
            )
        }
    }
}