package com.sgcdeveloper.chips.model.chips.imageChip

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sgcdeveloper.chips.model.ImagePosition

data class ChipImage(
    @DrawableRes val imageRes: Int? = null,
    val enableTint: Color = Color.Unspecified,
    val disableTint: Color = Color.Unspecified,
    val chipImagePosition: ImagePosition = ImagePosition.LEFT,
    val contextDescription: String? = "Chip image",
    val imageSize: Dp = 16.dp
)