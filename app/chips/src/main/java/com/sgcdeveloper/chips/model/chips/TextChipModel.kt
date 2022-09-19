package com.sgcdeveloper.chips.model.chips

open class TextChipModel(
    isActive: Boolean = false,
    val text: String = "",
) : ChipModel(isActive) {
    override fun <T : ChipModel> copy(isEnable: Boolean): T {
        return TextChipModel(isEnable, text) as T
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TextChipModel

        if (text != other.text) return false
        if (isEnable != other.isEnable) return false

        return true
    }

    override fun hashCode(): Int {
        return text.hashCode()
    }
}
