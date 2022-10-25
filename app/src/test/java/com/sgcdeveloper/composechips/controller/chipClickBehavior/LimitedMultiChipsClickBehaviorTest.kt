package com.sgcdeveloper.composechips.controller.chipClickBehavior

import com.sgcdeveloper.chips.controller.chipClickBehavior.LimitedMultiChipsClickBehavior
import com.sgcdeveloper.chips.model.chips.TextChipModel
import com.sgcdeveloper.chips.model.toText
import org.junit.Test

class LimitedMultiChipsClickBehaviorTest {

    private val chips = listOf(
        TextChipModel(isEnable = false, text = "A".toText(), id = "A"),
        TextChipModel(isEnable = false, text = "B".toText(), id = "B"),
        TextChipModel(isEnable = false, text = "C".toText(), id = "C"),
        TextChipModel(isEnable = false, text = "D".toText(), id = "D"),
        TextChipModel(isEnable = false, text = "E".toText(), id = "E"),
        TextChipModel(isEnable = false, text = "F".toText(), id = "F"),
        TextChipModel(isEnable = false, text = "G".toText(), id = "G"),
    )

    private val multipleChipsClickBehavior = LimitedMultiChipsClickBehavior()

    @Test
    fun check_click_first_chip() {
        val chip = chips.first()
        val updatedChips = multipleChipsClickBehavior.onChipClicked(chip, chips)
        assert(updatedChips.find { it.isEnable }?.copy<TextChipModel>(isEnable = false) == chip)
    }

    @Test
    fun check_click_last_chip() {
        val chip = chips.last()
        val updatedChips = multipleChipsClickBehavior.onChipClicked(chip, chips)
        assert(updatedChips.find { it.isEnable }?.copy<TextChipModel>(isEnable = false) == chip)
    }

    @Test
    fun check_multi_chips_enabled() {
        val firstChip = chips.first()
        val lastChip = chips.last()
        val updatedChips1 = multipleChipsClickBehavior.onChipClicked(firstChip, chips)
        val updatedChips2 = multipleChipsClickBehavior.onChipClicked(lastChip, updatedChips1)
        assert(updatedChips2.filter { it.isEnable }.size == 2)
        assert(updatedChips2.any { it == firstChip.copy<TextChipModel>(isEnable = true) })
        assert(updatedChips2.any { it == lastChip.copy<TextChipModel>(isEnable = true) })
    }

    @Test
    fun check_double_click_last_chip() {
        val chip = chips.last()
        val updatedChips = multipleChipsClickBehavior.onChipClicked(chip, chips)
        val updatedChips2 =
            multipleChipsClickBehavior.onChipClicked(chip.copy(isEnable = true), updatedChips)
        assert(updatedChips2.none { it.isEnable })
    }

    @Test
    fun check_hot_init() {
        val testChips = listOf(
            TextChipModel(isEnable = true, text = "A".toText(), id = "A"),
            TextChipModel(isEnable = false, text = "B".toText(), id = "B"),
            TextChipModel(isEnable = true, text = "C".toText(), id = "C"),
            TextChipModel(isEnable = false, text = "D".toText(), id = "D"),
            TextChipModel(isEnable = false, text = "E".toText(), id = "E"),
            TextChipModel(isEnable = true, text = "F".toText(), id = "F"),
            TextChipModel(isEnable = false, text = "G".toText(), id = "G"),
        )
        val chipsAfterHotInit = multipleChipsClickBehavior.hotInit(testChips)
        assert(chipsAfterHotInit.filter { it.isEnable } == testChips.filter { it.isEnable })
    }

}