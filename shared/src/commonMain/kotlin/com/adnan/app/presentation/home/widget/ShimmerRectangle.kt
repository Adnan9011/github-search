package com.adnan.app.presentation.home.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun ShimmerRectangle(
    isEnableShimmer: Boolean,
    isLightModeActive: Boolean = false,
    width: Dp,
    height: Dp,
    round: Dp,
    padding: Dp
) {
    Box(
        modifier = Modifier
            .padding(all = padding)
            .clip(shape = RoundedCornerShape(round))
            .background(color = Color.LightGray)
            .width(width)
            .height(height)
            .shimmerLoadingAnimation(
                isEnableShimmer = isEnableShimmer,
                isLightModeActive = isLightModeActive,
            )
    )
}