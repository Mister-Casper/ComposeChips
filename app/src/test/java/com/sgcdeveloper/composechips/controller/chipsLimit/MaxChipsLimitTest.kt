package com.sgcdeveloper.composechips.controller.chipsLimit

import com.sgcdeveloper.chips.controller.chipsLimit.MaxChipsLimit
import com.sgcdeveloper.chips.model.chips.ChipModel
import com.sgcdeveloper.chips.model.chips.TextChipModel
import com.sgcdeveloper.chips.model.toText
import org.junit.Test

class MaxChipsLimitTest {

    private val chips = listOf(
        TextChipModel(isActive = false, text = "A".toText()),
        TextChipModel(isActive = false, text = "B".toText()),
        TextChipModel(isActive = false, text = "C".toText()),
        TextChipModel(isActive = false, text = "D".toText()),
        TextChipModel(isActive = false, text = "E".toText()),
        TextChipModel(isActive = false, text = "F".toText()),
        TextChipModel(isActive = false, text = "G".toText()),
    )

    private val maxChipsLimit = MaxChipsLimit(3)

    @Test
    fun check_standard_order_work() {
        maxChipsLimit.addChip(chips[0])
        maxChipsLimit.addChip(chips[1])
        maxChipsLimit.addChip(chips[2])
        val limitedChips = maxChipsLimit.getEnabledChips<ChipModel>()
        assert(limitedChips[2] == chips[0])
        assert(limitedChips[1] == chips[1])
        assert(limitedChips[0] == chips[2])
    }

    @Test
    fun check_max_limit_work() {
        maxChipsLimit.addChip(chips[0])
        maxChipsLimit.addChip(chips[1])
        maxChipsLimit.addChip(chips[2])
        maxChipsLimit.addChip(chips[3])
        val limitedChips = maxChipsLimit.getEnabledChips<ChipModel>()
        assert(limitedChips[2] == chips[1])
        assert(limitedChips[1] == chips[2])
        assert(limitedChips[0] == chips[3])
    }

    @Test
    fun check_hot_init_without_new_enabled_items() {
        maxChipsLimit.addChip(chips[0])
        maxChipsLimit.addChip(chips[1])
        maxChipsLimit.addChip(chips[2])
        val limitedChips = maxChipsLimit.getEnabledChips<ChipModel>()
        assert(limitedChips[2] == chips[0])
        assert(limitedChips[1] == chips[1])
        assert(limitedChips[0] == chips[2])
        maxChipsLimit.hotInit(
            listOf(
                chips[0].copy(isEnable = true),
                chips[1].copy(isEnable = true),
                chips[2].copy(isEnable = true),
                chips[3].copy(isEnable = false)
            )
        )
        val updatedLimitedChips = maxChipsLimit.getEnabledChips<ChipModel>()
        assert(updatedLimitedChips[2] == chips[0])
        assert(updatedLimitedChips[1] == chips[1])
        assert(updatedLimitedChips[0] == chips[2])
        assert(limitedChips == updatedLimitedChips)
    }

    @Test
    fun check_hot_init_with_new_enabled_items() {
        maxChipsLimit.addChip(chips[0])
        maxChipsLimit.addChip(chips[1])
        maxChipsLimit.addChip(chips[2])
        val limitedChips = maxChipsLimit.getEnabledChips<ChipModel>()
        assert(limitedChips[2] == chips[0])
        assert(limitedChips[1] == chips[1])
        assert(limitedChips[0] == chips[2])
        maxChipsLimit.hotInit(
            listOf(
                chips[0].copy(isEnable = true),
                chips[1].copy(isEnable = true),
                chips[2].copy(isEnable = true),
                chips[3].copy(isEnable = true)
            )
        )
        val updatedLimitedChips = maxChipsLimit.getEnabledChips<ChipModel>()
        assert(updatedLimitedChips[2] == chips[0].copy(isEnable = true))
        assert(updatedLimitedChips[1] == chips[1].copy(isEnable = true))
        assert(updatedLimitedChips[0] == chips[2].copy(isEnable = true))
    }


}