package com.sgcdeveloper.chips.model.chips

import com.sgcdeveloper.chips.model.Text
import com.sgcdeveloper.chips.model.toText

open class TextChipModel(
    id: String = "",
    isEnable: Boolean = false,
    val text: Text = "".toText(),
) : ChipModel(id, isEnable) {
    override fun <T : ChipModel> copy(id: String, isEnable: Boolean): T {
        return TextChipModel(id, isEnable, text) as T
    }
}
