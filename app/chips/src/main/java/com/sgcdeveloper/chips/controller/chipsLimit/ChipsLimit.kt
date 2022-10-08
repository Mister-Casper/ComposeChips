package com.sgcdeveloper.chips.controller.chipsLimit

import com.sgcdeveloper.chips.model.chips.ChipModel

interface ChipsLimit {

    fun <T : ChipModel> addChip(chip: T)

    fun <T : ChipModel> getEnabledChips(): List<T>

    fun <T : ChipModel> hotInit(allChips: List<T>): List<T>

}