package com.sgcdeveloper.chips.model.chips.imageChip

import com.sgcdeveloper.chips.model.chips.ChipModel

open class ImageChipModel(
    isEnable: Boolean = false,
    val text: String = "",
    val image: ChipImage = ChipImage()
) : ChipModel(isEnable) {
    override fun <T : ChipModel> copy(isEnable: Boolean): T {
        return ImageChipModel(isEnable, text, image) as T
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ImageChipModel

        if(isEnable != other.isEnable) return false
        if (image != other.image) return false
        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        return image.hashCode()
    }
}
