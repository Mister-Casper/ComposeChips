package com.sgcdeveloper.chips.controller.chipClickBehavior

import com.sgcdeveloper.chips.model.chips.ChipModel

class SingleChipClickBehavior(chips: List<ChipModel>) : ChipClickBehavior {

    init {
        hotInit(chips)
    }

    override fun <T : ChipModel> onChipClicked(chip: T, allChips: List<T>): List<T> {
        return allChips.map { item -> item.copy(isEnable = (item == chip)) as T }
    }

    override fun <T : ChipModel> hotInit(allChips: List<T>): List<T> {
        if (isNotOnlyOneChipEnable(allChips)) {
            var isOnlyOneChipEnable = false
            return allChips.map {
                if (isFirstEnabledChip(isOnlyOneChipEnable, it)) {
                    isOnlyOneChipEnable = true
                    it
                } else {
                    it.copy(isEnable = false)
                }
            }
        }
        return allChips
    }

    private fun isFirstEnabledChip(isOnlyOneChipEnable: Boolean, chip: ChipModel): Boolean {
        return !isOnlyOneChipEnable && chip.isEnable
    }

    private fun isNotOnlyOneChipEnable(chips: List<ChipModel>) = chips.filter { it.isEnable }.size > 1
}