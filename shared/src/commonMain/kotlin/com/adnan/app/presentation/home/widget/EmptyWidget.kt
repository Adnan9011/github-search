package com.adnan.app.presentation.home.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.adnan.app.presentation.util.EmptySize
import com.adnan.app.presentation.util.ItemHeight
import com.adnan.app.presentation.util.NOT_FOUND
import com.adnan.app.presentation.util.NormalPadding
import com.adnan.app.presentation.util.WARNING_TextStyle

@Composable
fun EmptyWidget() {
    Column(
        modifier = Modifier
            .padding(NormalPadding)
            .fillMaxWidth()
            .height(ItemHeight),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier
                .size(EmptySize),
            imageVector = Icons.Filled.Warning,
            tint = Color.DarkGray,
            contentDescription = null
        )
        Divider(
            Modifier
                .height(NormalPadding),
            color = Color.Transparent
        )
        Text(
            text = NOT_FOUND,
            style = WARNING_TextStyle
        )
    }
}