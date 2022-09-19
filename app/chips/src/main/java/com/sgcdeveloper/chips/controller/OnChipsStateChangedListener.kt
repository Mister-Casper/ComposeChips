package com.sgcdeveloper.chips.controller

import com.sgcdeveloper.chips.model.chips.ChipModel

interface OnChipsChangedListener {

    fun <T: ChipModel> onChipsChanged(chips: List<T>)

}