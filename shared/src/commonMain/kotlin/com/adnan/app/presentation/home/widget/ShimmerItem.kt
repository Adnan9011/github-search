package com.adnan.app.presentation.home.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.adnan.app.presentation.util.ItemHeight
import com.adnan.app.presentation.util.ItemRound
import com.adnan.app.presentation.util.ItemWidth
import com.adnan.app.presentation.util.ShimmerRectangleHeight
import com.adnan.app.presentation.util.SmallPadding

@Composable
fun ShimmerRepoItem(
    isEnableShimmer: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(size = ItemRound))
    ) {
        ShimmerRectangle(
            isEnableShimmer = isEnableShimmer,
            width = ItemWidth,
            height = ItemHeight,
            round = ItemRound,
            padding = SmallPadding
        )

        Column {
            ShimmerRectangle(
                isEnableShimmer = isEnableShimmer,
                width = ItemWidth,
                height = ShimmerRectangleHeight,
                round = ItemRound,
                padding = SmallPadding
            )

            ShimmerRectangle(
                isEnableShimmer = isEnableShimmer,
                width = ItemWidth,
                height = ShimmerRectangleHeight,
                round = ItemRound,
                padding = SmallPadding
            )
        }
    }
}