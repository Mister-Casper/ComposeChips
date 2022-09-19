package com.sgcdeveloper.chips.controller.chipsLimit

import com.sgcdeveloper.chips.model.chips.ChipModel

open class MaxChipsLimit(
    private val maxChips: Int = Int.MAX_VALUE
) : ChipsLimit {

    private val chipsQueue = ArrayDeque<ChipModel>()

    override fun addChip(chip: ChipModel) {
        if (isChipLimitReached(chip)) {
            chipsQueue.removeLast()
        }

        if (chip.isEnable) {
            chipsQueue.remove(chip)
        } else {
            chipsQueue.addFirst(chip.copy(isEnable = true))
        }
    }

    override fun getEnabledChips(): List<ChipModel> {
        return chipsQueue.toList()
    }

    override fun hotInit(allChips: List<ChipModel>) {
        val enabledChips = allChips.filter { it.isEnable }
        if (enabledChips != chipsQueue) {
            chipsQueue.clear()
            chipsQueue.addAll(enabledChips.reversed().map { it })
        }
    }

    private fun isChipLimitReached(chip: ChipModel): Boolean {
        return chipsQueue.size >= maxChips && !chip.isEnable
    }

}