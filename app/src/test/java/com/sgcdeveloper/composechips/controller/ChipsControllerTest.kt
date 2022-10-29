package com.sgcdeveloper.composechips.controller

import com.sgcdeveloper.chips.controller.ChipsController
import com.sgcdeveloper.chips.controller.chipClickBehavior.SingleChipClickBehavior
import com.sgcdeveloper.chips.model.chips.TextChipModel
import com.sgcdeveloper.chips.model.toText
import org.junit.Test

class ChipsControllerTest {

    private val chips = listOf(
        TextChipModel(isEnable = false, text = "A".toText(), id = "A"),
        TextChipModel(isEnable = false, text = "B".toText(), id = "B"),
        TextChipModel(isEnable = false, text = "C".toText(), id = "C"),
        TextChipModel(isEnable = false, text = "D".toText(), id = "D"),
        TextChipModel(isEnable = false, text = "E".toText(), id = "E"),
        TextChipModel(isEnable = false, text = "F".toText(), id = "F"),
        TextChipModel(isEnable = false, text = "G".toText(), id = "G"),
    )

    private val сhipsController = ChipsController(chips)




}