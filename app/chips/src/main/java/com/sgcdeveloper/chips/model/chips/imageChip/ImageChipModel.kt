package com.sgcdeveloper.chips.model.chips.imageChip

import com.sgcdeveloper.chips.model.Text
import com.sgcdeveloper.chips.model.chips.ChipModel
import com.sgcdeveloper.chips.model.toText

open class ImageChipModel(
    isEnable: Boolean = false,
    id: String = "",
    val text: Text = "".toText(),
    val leftImage: ChipImage? = null,
    val rightImage: ChipImage? = null,
) : ChipModel(id, isEnable) {
    override fun <T : ChipModel> copy(id: String, isEnable: Boolean): T {
        return ImageChipModel(isEnable, id, text, leftImage, rightImage) as T
    }
}
