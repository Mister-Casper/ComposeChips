package com.sgcdeveloper.chips.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.sgcdeveloper.chips.model.ChipModel
import com.sgcdeveloper.chips.model.TextChipModel

@Composable
fun <T : ChipModel> ChipsRow(
    textChips: List<T>,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = ChipDefaults.DefaultShape,
    border: BorderStroke? = null,
    colors: ChipColors = ChipDefaults.buttonColors(),
    contentPadding: PaddingValues = ChipDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit,
    chipPadding: PaddingValues = ChipDefaults.ChipPaddings,
    onClick: (chipModel: T) -> Unit
) {
    LazyRow(Modifier.fillMaxWidth()) {
        items(textChips, key = { it.id }) { chip ->
            Chip(
                textChip = chip,
                modifier = modifier,
                interactionSource = interactionSource,
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
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = ChipDefaults.DefaultShape,
    border: BorderStroke? = null,
    colors: ChipColors = ChipDefaults.buttonColors(),
    contentPadding: PaddingValues = ChipDefaults.ContentPadding,
    chipPadding: PaddingValues = ChipDefaults.ChipPaddings,
    onClick: (textChipModel: T) -> Unit
) {
    LazyRow(Modifier.fillMaxWidth()) {
        items(textChips, key = { it.id }) { chip ->
            Chip(
                textChip = chip,
                modifier = modifier,
                interactionSource = interactionSource,
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Chip(
    textChip: ChipModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = ChipDefaults.DefaultShape,
    colors: ChipColors = ChipDefaults.buttonColors(),
    border: BorderStroke? = BorderStroke(1.dp, colors.borderColor(textChip.isEnable).value),
    contentPadding: PaddingValues = ChipDefaults.ContentPadding,
    chipPadding: PaddingValues = ChipDefaults.ChipPaddings,
    content: @Composable RowScope.() -> Unit
) {
    val isEnable = textChip.isEnable
    val contentColor by colors.contentColor(isEnable)
    val backgroundColor by colors.backgroundColor(isEnable)
    rememberRipple()
    Surface(
        onClick = onClick,
        modifier = modifier.padding(chipPadding),
        enabled = isEnable,
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor.copy(alpha = 1f),
        border = border,
        interactionSource = interactionSource
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