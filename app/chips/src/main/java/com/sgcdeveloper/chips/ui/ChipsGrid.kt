package com.sgcdeveloper.chips.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.sgcdeveloper.chips.model.chips.ChipModel
import com.sgcdeveloper.chips.model.chips.TextChipModel
import com.sgcdeveloper.chips.model.chips.imageChip.ImageChipModel

@Composable
fun <T : ImageChipModel> ImageTextChipsGrid(
    chips: List<T>,
    modifier: Modifier = Modifier,
    shape: Shape = ChipDefaults.DefaultShape,
    border: BorderStroke? = null,
    colors: ChipColors = ChipDefaults.buttonColors(),
    contentPadding: PaddingValues = ChipDefaults.ContentPadding,
    chipPadding: PaddingValues = ChipDefaults.ChipPaddings,
    onClick: (chipModel: T) -> Unit
) {
    ChipsGrid(
        chips = chips,
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

@Composable
fun <T : TextChipModel> TextChipsGrid(
    chips: List<T>,
    modifier: Modifier = Modifier,
    shape: Shape = ChipDefaults.DefaultShape,
    border: BorderStroke? = null,
    colors: ChipColors = ChipDefaults.buttonColors(),
    contentPadding: PaddingValues = ChipDefaults.ContentPadding,
    chipPadding: PaddingValues = ChipDefaults.ChipPaddings,
    onClick: (chipModel: T) -> Unit
) {
    ChipsGrid(
        chips = chips,
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
fun <T : ChipModel> ChipsGrid(
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
    FlowRow(
        modifier = modifier,
    ) {
        chips.forEach {
            Chip(
                chip = it,
                modifier = modifier,
                shape = shape,
                border = border,
                colors = colors,
                contentPadding = contentPadding,
                chipPadding = chipPadding,
                content = content,
                onClick = { onClick(it) }
            )
        }
    }
}
