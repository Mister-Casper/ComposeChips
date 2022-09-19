package com.sgcdeveloper.chips.controller.chipsLimit

import com.sgcdeveloper.chips.model.ChipModel

interface ChipsLimit {

    fun addChip(chip: ChipModel)

    fun getEnabledChips(): List<String>

    fun hotInit(allChips: List<ChipModel>)

}