package com.sgcdeveloper.chips.controller.chipsLimit

import com.sgcdeveloper.chips.model.chips.ChipModel

interface ChipsLimit {

    fun addChip(chip: ChipModel)

    fun getEnabledChips(): List<ChipModel>

    fun hotInit(allChips: List<ChipModel>)

}