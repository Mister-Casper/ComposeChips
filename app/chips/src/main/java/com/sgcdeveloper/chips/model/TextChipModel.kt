package com.sgcdeveloper.chips.model

import androidx.annotation.DrawableRes

abstract class ChipModel(val isEnable: Boolean, val id: String) {
    abstract fun <T : ChipModel> copy(isEnable: Boolean): T
}

open class TextChipModel(
    isActive: Boolean = false,
    id: String = "",
    val text: String = "",
) : ChipModel(isActive, id) {
    override fun <T : ChipModel> copy(isEnable: Boolean): T {
        return TextChipModel(isEnable, id, text) as T
    }
}

open class ImageChipModel(
    isActive: Boolean = false,
    id: String = "",
    @DrawableRes val image: Int = 0,
) : ChipModel(isActive, id) {
    override fun <T : ChipModel> copy(isEnable: Boolean): T {
        return ImageChipModel(isEnable, id, image) as T
    }
}