package com.example.heroapp.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heroapp.ui.theme.*
import com.example.heroapp.ui.theme.NAME_SHIMMER_PLACEHOLDER


@Composable
fun ShimmerEffect() {

}

@Composable
fun AnimatedShimmerEffect() {
    val transition = rememberInfiniteTransition()
    val alphaAnimation by transition.animateFloat(
        initialValue = 1f, // si inizia con il 100% di visibilità,
        targetValue = 0f, // si finisce con lo 0% di visibilità,
        animationSpec = infiniteRepeatable(
            animation = tween( durationMillis = 500, easing = FastOutLinearInEasing ),
            repeatMode = RepeatMode.Reverse
        )
    )
    ShimmerItem(alpha = alphaAnimation)
}

@Composable
fun ShimmerItem(alpha: Float) {
    Surface(
        modifier = Modifier.fillMaxWidth().height(HERO_ITEM_HIGHT),
        color = if (isSystemInDarkTheme()) Color.Black else Color.LightGray,
        shape = RoundedCornerShape(size = MEDIUM_PADDING)
    ){
        Column(
            modifier = Modifier.fillMaxWidth().padding(MEDIUM_PADDING),
            verticalArrangement = Arrangement.Bottom
        ) {
            Surface(
                // fill the 50% of the max width
                modifier = Modifier.fillMaxWidth(0.5f)
                    .height(NAME_SHIMMER_PLACEHOLDER)
                    .alpha(alpha),
                color = if (isSystemInDarkTheme()) Color.DarkGray else Color.Gray,
                shape = RoundedCornerShape(size = SMALL_PADDING)
            ) { }
            Spacer(modifier = Modifier.padding(SMALL_PADDING))
            repeat(3) {
                Surface(
                    modifier = Modifier.fillMaxWidth()
                        .height(DESC_SHIMMER_PLACEHOLDER)
                        .alpha(alpha),
                    color = if (isSystemInDarkTheme()) Color.DarkGray else Color.Gray,
                    shape = RoundedCornerShape(size = SMALL_PADDING)
                ) { }
                Spacer(modifier = Modifier.padding(EXTRA_SMALL_PADDING))
            }
            Spacer(modifier = Modifier.padding(EXTRA_SMALL_PADDING))
            Row() {
                repeat(5) {
                    Surface(
                        modifier = Modifier.size(30.dp).alpha(alpha),
                        color = if (isSystemInDarkTheme()) Color.DarkGray else Color.Gray,
                        shape = RoundedCornerShape(size = SMALL_PADDING)
                    ) { }
                    Spacer(modifier = Modifier.padding(EXTRA_SMALL_PADDING))
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ShimmerItemPreview() {
    AnimatedShimmerEffect()
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun ShimmerItemDarkPreview() {
    AnimatedShimmerEffect()
}