package com.adnan.app.presentation.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val HOME_TITLE = "Result of your search :"
const val NOT_FOUND = "Repo Not Found"
const val ERROR = "Warning ..."

const val ShimmerRepoItemCount = 5
val ShimmerRectangleHeight = 30.dp

val TinyPadding = 4.dp
val SmallPadding = 8.dp
val NormalPadding = 16.dp
val HighPadding = 32.dp

val SmallElevation = 2.dp

val ItemWidth = 140.dp
val ItemHeight = 100.dp

val FavoriteIcon = 48.dp

val EmptySize = 60.dp

val ItemRound = 20.dp

val HomeTextTitleStyle =
    TextStyle(
        fontSize = 20.sp,
        color = Color.Black,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold
    )

val WARNING_TextStyle =
    TextStyle(
        fontSize = 16.sp,
        color = Color.Black,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Justify,
        lineHeight = 20.sp
    )

val DetailItemTextStyle =
    TextStyle(
        fontSize = 12.sp,
        color = Color.Black,
        fontFamily = FontFamily.Serif,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Bold
    )