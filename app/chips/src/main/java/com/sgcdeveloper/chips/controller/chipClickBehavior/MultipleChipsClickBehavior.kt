package com.sgcdeveloper.chips.controller.chipClickBehavior

import com.sgcdeveloper.chips.controller.chipsLimit.MaxChipsLimit
import com.sgcdeveloper.chips.model.ChipModel

class MultipleChipsClickBehavior(private val maxChipsLimit: MaxChipsLimit?) : ChipClickBehavior {

    override fun <T : ChipModel> onChipClicked(chip: T, allChips: List<T>): List<T> {
        maxChipsLimit?.addChip(chip)
        return allChips.map { item ->
            if (item.id == chip.id) {
                chip.copy(isEnable = !chip.isEnable)
            } else {
                item
            }
        }
    }

    override fun <T : ChipModel> hotInit(allChips: List<T>): List<T> {
        maxChipsLimit?.hotInit(allChips)
        return allChips
    }

}