package com.sgcdeveloper.chips.model.chips

import com.sgcdeveloper.chips.model.Text
import com.sgcdeveloper.chips.model.toText

open class TextChipModel(
    isActive: Boolean = false,
    id: String = "",
    val text: Text = "".toText(),
) : ChipModel(id, isActive) {
    override fun <T : ChipModel> copy(id: String, isEnable: Boolean): T {
        return TextChipModel(isEnable, id, text) as T
    }
}
