package com.sgcdeveloper.chips.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sgcdeveloper.chips.model.ImagePosition
import com.sgcdeveloper.chips.model.chips.ChipModel
import com.sgcdeveloper.chips.model.chips.TextChipModel
import com.sgcdeveloper.chips.model.chips.imageChip.ChipImage
import com.sgcdeveloper.chips.model.chips.imageChip.ImageChipModel

@Composable
fun <T : ChipModel> ChipsRow(
    textChips: List<T>,
    modifier: Modifier = Modifier,
    shape: Shape = ChipDefaults.DefaultShape,
    border: BorderStroke? = null,
    colors: ChipColors = ChipDefaults.buttonColors(),
    contentPadding: PaddingValues = ChipDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
    chipPadding: PaddingValues = ChipDefaults.ChipPaddings,
    onClick: (chipModel: T) -> Unit
) {
    LazyRow(Modifier.fillMaxWidth()) {
        items(textChips) { chip ->
            Chip(
                textChip = chip,
                modifier = modifier,
                shape = shape,
                border = border,
                colors = colors,
                contentPadding = contentPadding,
                chipPadding = chipPadding,
                content = content,
                onClick = { onClick(chip) }
            )
        }
    }
}

@Composable
fun <T : TextChipModel> TextChipsRow(
    textChips: List<T>,
    modifier: Modifier = Modifier,
    shape: Shape = ChipDefaults.DefaultShape,
    border: BorderStroke? = null,
    colors: ChipColors = ChipDefaults.buttonColors(),
    contentPadding: PaddingValues = ChipDefaults.ContentPadding,
    chipPadding: PaddingValues = ChipDefaults.ChipPaddings,
    onClick: (textChipModel: T) -> Unit
) {
    LazyRow(Modifier.fillMaxWidth()) {
        items(textChips) { chip ->
            Chip(
                textChip = chip,
                modifier = modifier,
                shape = shape,
                border = border,
                colors = colors,
                contentPadding = contentPadding,
                chipPadding = chipPadding,
                content = { Text(text = chip.text) },
                onClick = { onClick(chip) }
            )
        }
    }
}

@Composable
fun <T : ImageChipModel> ImageChipsRow(
    textChips: List<T>,
    modifier: Modifier = Modifier,
    shape: Shape = ChipDefaults.DefaultShape,
    border: BorderStroke? = null,
    colors: ChipColors = ChipDefaults.buttonColors(),
    contentPadding: PaddingValues = ChipDefaults.ContentPadding,
    chipPadding: PaddingValues = ChipDefaults.ChipPaddings,
    onClick: (textChipModel: T) -> Unit
) {
    LazyRow(Modifier.fillMaxWidth()) {
        items(textChips) { chip ->
            Chip(
                textChip = chip,
                modifier = modifier,
                shape = shape,
                border = border,
                colors = colors,
                contentPadding = contentPadding,
                chipPadding = chipPadding,
                content = {
                    ChipImage(chip, ImagePosition.LEFT)
                    Text(text = chip.text)
                    ChipImage(chip, ImagePosition.RIGHT)
                },
                onClick = { onClick(chip) }
            )
        }
    }
}

@Composable
fun RowScope.ChipImage(chip: ImageChipModel, position: ImagePosition) {
    val colorTint = if (chip.isEnable) {
        chip.image.enableTint
    } else {
        chip.image.disableTint
    }

    if (chip.image.chipImagePosition == position && chip.image.imageRes != null) {
        Icon(
            painter = painterResource(id = chip.image.imageRes),
            tint = colorTint,
            contentDescription = chip.image.contextDescription,
            modifier = Modifier
                .size(chip.image.imageSize)
                .align(Alignment.CenterVertically)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Chip(
    textChip: ChipModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = ChipDefaults.DefaultShape,
    colors: ChipColors = ChipDefaults.buttonColors(),
    border: BorderStroke?,
    contentPadding: PaddingValues = ChipDefaults.ContentPadding,
    chipPadding: PaddingValues = ChipDefaults.ChipPaddings,
    content: @Composable RowScope.() -> Unit
) {
    val isEnable = textChip.isEnable
    val contentColor by colors.contentColor(isEnable)
    val backgroundColor by colors.backgroundColor(isEnable)
    val borderColor = colors.borderColor(isEnable).value
    Surface(
        onClick = onClick,
        modifier = modifier.padding(chipPadding),
        enabled = true,
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor.copy(alpha = 1f),
        border = border ?: BorderStroke(1.dp, borderColor),
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
                    content = content
                )
            }
        }
    }
}