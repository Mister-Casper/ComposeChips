package com.sgcdeveloper.chips.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.sgcdeveloper.chips.model.chips.ChipModel
import com.sgcdeveloper.chips.model.chips.TextChipModel
import com.sgcdeveloper.chips.model.chips.imageChip.ImageChipModel

@Composable
fun <T : ChipModel> ChipsRow(
    chips: List<T>,
    modifier: Modifier = Modifier,
    shape: Shape = ChipDefaults.DefaultShape,
    border: BorderStroke? = null,
    colors: ChipColors = ChipDefaults.buttonColors(),
    contentPadding: PaddingValues = ChipDefaults.ContentPadding,
    content: @Composable RowScope.(chipModel: T) -> Unit,
    chipPadding: PaddingValues = ChipDefaults.ChipPaddings,
    onClick: (chipModel: T) -> Unit
) {
    LazyRow(Modifier.fillMaxWidth()) {
        items(chips, key = { it.id }) { chip ->
            Chip(
                chip = chip,
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
    ChipsRow(
        chips = textChips,
        modifier = modifier,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        chipPadding = chipPadding,
        content = { chip -> Text(text = chip.text) },
        onClick = { chip -> onClick(chip) }
    )
}

@Composable
fun <T : ImageChipModel> ImageChipsRow(
    imageChips: List<T>,
    modifier: Modifier = Modifier,
    shape: Shape = ChipDefaults.DefaultShape,
    border: BorderStroke? = null,
    colors: ChipColors = ChipDefaults.buttonColors(),
    contentPadding: PaddingValues = ChipDefaults.ContentPadding,
    chipPadding: PaddingValues = ChipDefaults.ChipPaddings,
    onClick: (textChipModel: T) -> Unit
) {
    ChipsRow(
        chips = imageChips,
        modifier = modifier,
        shape = shape,
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        chipPadding = chipPadding,
        content = { chip ->
            ChipImage(chip.leftImage, chip.isEnable)
            Spacer(modifier = Modifier.width(chip.leftImage?.padding ?: 0.dp))
            Text(text = chip.text)
            Spacer(modifier = Modifier.width(chip.rightImage?.padding ?: 0.dp))
            ChipImage(chip.rightImage, chip.isEnable)
        },
        onClick = { chip -> onClick(chip) }
    )
}