package com.sgcdeveloper.chips.controller.chipClickBehavior

import com.sgcdeveloper.chips.model.chips.ChipModel

class MultipleChipsClickBehavior() : ChipClickBehavior {

    override fun <T : ChipModel> onChipClicked(chip: T, allChips: List<T>): List<T> {
        return allChips.map { item ->
            if (item == chip) {
                chip.copy(isEnable = !chip.isEnable)
            } else {
                item
            }
        }
    }

    override fun <T : ChipModel> hotInit(allChips: List<T>): List<T> {
        return allChips.toList()
    }

}