package com.sgcdeveloper.chips.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp

object ChipDefaults {

    private val ChipContentHorizontalPadding = 16.dp
    private val ChipContentVerticalPadding = 8.dp
    private val ChipHorizontalPadding = 4.dp
    private val ChipVerticalPadding = 4.dp

    val ContentPadding = PaddingValues(
        start = ChipContentHorizontalPadding,
        top = ChipContentVerticalPadding,
        end = ChipContentHorizontalPadding,
        bottom = ChipContentVerticalPadding
    )

    val ChipPaddings = PaddingValues(
        start = ChipHorizontalPadding,
        top = ChipVerticalPadding,
        end = ChipHorizontalPadding,
        bottom = ChipVerticalPadding
    )

    val DefaultShape = RoundedCornerShape(36.dp)

    val MinWidth = 64.dp
    val MinHeight = 36.dp

    @Composable
    fun chipColors(
        backgroundColor: Color = MaterialTheme.colors.primary,
        contentColor: Color =  MaterialTheme.colors.onSurface,
        disabledBackgroundColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.12f)
            .compositeOver(MaterialTheme.colors.surface),
        disabledContentColor: Color = MaterialTheme.colors.onSurface
            .copy(alpha = ContentAlpha.disabled),
        borderColor: Color = MaterialTheme.colors.onSurface,
        disabledBorderColor: Color = MaterialTheme.colors.onSurface
            .copy(alpha = ContentAlpha.disabled)
    ): ChipColors = DefaultChipColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledBackgroundColor = disabledBackgroundColor,
        disabledContentColor = disabledContentColor,
        borderColor = borderColor,
        disabledBorderColor = disabledBorderColor
    )
}

@Immutable
private class DefaultChipColors(
    private val backgroundColor: Color,
    private val contentColor: Color,
    private val disabledBackgroundColor: Color,
    private val disabledContentColor: Color,
    private val disabledBorderColor: Color,
    private val borderColor: Color,
) : ChipColors {
    @Composable
    override fun backgroundColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) backgroundColor else disabledBackgroundColor)
    }

    @Composable
    override fun contentColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) contentColor else disabledContentColor)
    }

    @Composable
    override fun borderColor(enabled: Boolean): State<Color> {
        return rememberUpdatedState(if (enabled) borderColor else disabledBorderColor)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as DefaultChipColors

        if (backgroundColor != other.backgroundColor) return false
        if (contentColor != other.contentColor) return false
        if (borderColor != other.borderColor) return false
        if (disabledBackgroundColor != other.disabledBackgroundColor) return false
        if (disabledContentColor != other.disabledContentColor) return false
        if (disabledBorderColor != other.disabledBorderColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = backgroundColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        result = 31 * result + disabledBackgroundColor.hashCode()
        result = 31 * result + disabledContentColor.hashCode()
        return result
    }
}

interface ChipColors {
    @Composable
    fun backgroundColor(enabled: Boolean): State<Color>

    @Composable
    fun contentColor(enabled: Boolean): State<Color>

    @Composable
    fun borderColor(enabled: Boolean): State<Color>
}
