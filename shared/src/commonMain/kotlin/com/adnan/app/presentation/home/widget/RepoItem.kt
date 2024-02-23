package com.adnan.app.presentation.home.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.twotone.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import com.adnan.app.presentation.model.RepoUiModel
import com.adnan.app.presentation.util.DetailItemTextStyle
import com.adnan.app.presentation.util.FavoriteIcon
import com.adnan.app.presentation.util.ItemHeight
import com.adnan.app.presentation.util.ItemRound
import com.adnan.app.presentation.util.ItemWidth
import com.adnan.app.presentation.util.NormalPadding
import com.adnan.app.presentation.util.SmallElevation
import com.adnan.app.presentation.util.SmallPadding

@Composable
fun RepoItem(
    repo: RepoUiModel,
    onRepoClicked: (RepoUiModel) -> Unit
) {
    var isLiked by remember { mutableStateOf(repo.isLiked) }

    Surface(
        modifier = Modifier
            .background(Color.White)
            .padding(all = SmallPadding)
            .fillMaxWidth()
            .clickable {
                isLiked = !isLiked
                onRepoClicked(repo.copy(isLiked = isLiked))
            }
            .clip(RoundedCornerShape(size = ItemRound)),
        shadowElevation = SmallElevation,
        tonalElevation = SmallElevation
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(ItemHeight)
        ) {

            Image(
                modifier = Modifier
                    .size(width = ItemWidth, height = ItemHeight),
                painter = AsyncImagePainter(repo.avatarUrl),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight()
            ) {

                Text(
                    text = repo.name,
                    style = DetailItemTextStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = SmallPadding,
                            bottom = SmallPadding,
                            start = NormalPadding,
                            end = NormalPadding
                        ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Text(
                    modifier = Modifier
                        .padding(
                            top = SmallPadding,
                            bottom = SmallPadding,
                            start = NormalPadding,
                            end = NormalPadding
                        ),
                    text = "Number of Forks: ${repo.forksNumber}",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    style = DetailItemTextStyle,
                )
            }
            Icon(
                imageVector = if (isLiked) Icons.Rounded.Star else Icons.TwoTone.Star,
                tint = Color.Red,
                modifier = Modifier
                    .padding(
                        end = SmallPadding
                    )
                    .size(FavoriteIcon),
                contentDescription = null
            )

        }
    }
}