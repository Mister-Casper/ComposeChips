package com.sgcdeveloper.composechips.controller.chipClickBehavior

import com.sgcdeveloper.chips.controller.chipClickBehavior.SingleChipClickBehavior
import com.sgcdeveloper.chips.model.chips.TextChipModel
import com.sgcdeveloper.chips.model.toText
import org.junit.Test

class SingleChipClickBehaviorTest {

    private val chips = listOf(
        TextChipModel(isEnable = false, text = "A".toText(), id = "A"),
        TextChipModel(isEnable = false, text = "B".toText(), id = "B"),
        TextChipModel(isEnable = false, text = "C".toText(), id = "C"),
        TextChipModel(isEnable = false, text = "D".toText(), id = "D"),
        TextChipModel(isEnable = false, text = "E".toText(), id = "E"),
        TextChipModel(isEnable = false, text = "F".toText(), id = "F"),
        TextChipModel(isEnable = false, text = "G".toText(), id = "G"),
    )

    private val singleChipClickBehavior = SingleChipClickBehavior()

    @Test
    fun check_click_first_chip() {
        val chip = chips.first()
        val updatedChips = singleChipClickBehavior.onChipClicked(chip, chips)
        assert(updatedChips.find { it.isEnable }?.copy<TextChipModel>(isEnable = false) == chip)
    }

    @Test
    fun check_click_last_chip() {
        val chip = chips.last()
        val updatedChips = singleChipClickBehavior.onChipClicked(chip, chips)
        assert(updatedChips.find { it.isEnable }?.copy<TextChipModel>(isEnable = false) == chip)
    }

    @Test
    fun check_only_one_chip_enabled() {
        val firstChip = chips.first()
        val lastChip = chips.last()
        val updatedChips1 = singleChipClickBehavior.onChipClicked(firstChip, chips)
        val updatedChips2 = singleChipClickBehavior.onChipClicked(lastChip, updatedChips1)
        assert(updatedChips2.filter { it.isEnable }.size == 1)
    }

    @Test
    fun check_two_click_on_different_chips() {
        val firstChip = chips.first()
        val lastChip = chips.last()
        val updatedChips1 = singleChipClickBehavior.onChipClicked(firstChip, chips)
        val updatedChips2 = singleChipClickBehavior.onChipClicked(lastChip, updatedChips1)
        assert(updatedChips2.find { it.isEnable }
            ?.copy<TextChipModel>(isEnable = false) == lastChip)
    }

    @Test
    fun check_double_click_last_chip() {
        val chip = chips.last()
        val updatedChips = singleChipClickBehavior.onChipClicked(chip, chips)
        val updatedChips2 =
            singleChipClickBehavior.onChipClicked(chip.copy(isEnable = true), updatedChips)
        assert(updatedChips2.find { it.isEnable }?.copy<TextChipModel>(isEnable = false) == chip)
        assert(updatedChips2.filter { it.isEnable }.size == 1)
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
        val firstEnabledChips = testChips.first()
        val chipsAfterHotInit = singleChipClickBehavior.hotInit(testChips)
        assert(chipsAfterHotInit.filter { it.isEnable }.size == 1)
        assert(chipsAfterHotInit.find { it.isEnable } == firstEnabledChips)
    }

}