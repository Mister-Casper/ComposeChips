package com.sgcdeveloper.chips.model.chips.imageChip

import com.sgcdeveloper.chips.model.chips.ChipModel

open class ImageChipModel(
    isEnable: Boolean = false,
    val text: String = "",
    val leftImage: ChipImage? = null,
    val rightImage: ChipImage? = null,
) : ChipModel(isEnable) {
    override fun <T : ChipModel> copy(isEnable: Boolean): T {
        return ImageChipModel(isEnable, text, leftImage, rightImage) as T
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ImageChipModel

        if (isEnable != other.isEnable) return false
        if (leftImage != other.leftImage) return false
        if (rightImage != other.rightImage) return false
        if (text != other.text) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isEnable.hashCode()
        result = 31 * result + (leftImage?.hashCode() ?: 0)
        result = 31 * result + (rightImage?.hashCode() ?: 0)
        result = 31 * result + text.hashCode()
        return result
    }
}
