package com.sgcdeveloper.chips.controller.chipClickBehavior

import com.sgcdeveloper.chips.model.chips.ChipModel

interface ChipClickBehavior {

    fun <T : ChipModel> onChipClicked(chip: T, allChips: List<T>): List<T>

    fun <T : ChipModel> hotInit(allChips: List<T>): List<T>

}