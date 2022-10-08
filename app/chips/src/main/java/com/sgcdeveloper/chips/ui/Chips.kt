package com.sgcdeveloper.chips.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sgcdeveloper.chips.model.chips.ChipModel
import com.sgcdeveloper.chips.model.chips.imageChip.ChipImage

@Composable
fun RowScope.ChipImage(chipImage: ChipImage?, isEnable: Boolean) {
    if (chipImage == null) return

    val colorTint = if (isEnable) {
        chipImage.enableTint
    } else {
        chipImage.disableTint
    }

    Icon(
        painter = painterResource(id = chipImage.imageRes),
        tint = colorTint,
        contentDescription = chipImage.contextDescription,
        modifier = Modifier
            .size(chipImage.imageSize)
            .align(Alignment.CenterVertically)
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T : ChipModel> Chip(
    chip: T,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = ChipDefaults.DefaultShape,
    colors: ChipColors = ChipDefaults.buttonColors(),
    border: BorderStroke?,
    contentPadding: PaddingValues = ChipDefaults.ContentPadding,
    chipPadding: PaddingValues = ChipDefaults.ChipPaddings,
    content: @Composable RowScope.(chipModel: T) -> Unit
) {
    val isEnable = chip.isEnable
    val contentColor by colors.contentColor(isEnable)
    val backgroundColor by colors.backgroundColor(isEnable)
    val borderColor by colors.borderColor(isEnable)
    Surface(
        onClick = onClick,
        modifier = modifier.padding(chipPadding),
        enabled = true,
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor.copy(alpha = 1f),
        border = border ?: BorderStroke(1.dp, borderColor)
    ) {
        CompositionLocalProvider(LocalContentAlpha provides contentColor.alpha) {
            ProvideTextStyle(
                value = MaterialTheme.typography.button
            ) {
                Row(
                    Modifier
                        .defaultMinSize(
                            minWidth = ChipDefaults.MinWidth,
                            minHeight = ChipDefaults.MinHeight
                        )
                        .padding(contentPadding),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    content = { content(chip) }
                )
            }
        }
    }
}