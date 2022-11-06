package com.sgcdeveloper.chips.model.chips.imageChip

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

data class ChipImage(
    @DrawableRes val imageRes: Int,
    val enableTint: Color = Color.Unspecified,
    val disableTint: Color = Color.Unspecified,
    val contextDescription: String? = "Chip image",
    val imageSize: DpSize = DpSize(16.dp, 16.dp),
    val padding: Dp = 8.dp
)