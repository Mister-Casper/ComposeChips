package com.sgcdeveloper.chips.controller.chipsLimit

import com.sgcdeveloper.chips.model.ChipModel

open class MaxChipsLimit(
    private val maxChips: Int = Int.MAX_VALUE
) : ChipsLimit {

    private val chipsQueue = ArrayDeque<String>()

    override fun addChip(chip: ChipModel) {
        if (isChipLimitReached(chip)) {
            chipsQueue.removeLast()
        }

        chipsQueue.addFirst(chip.id)
    }

    override fun hotInit(allChips: List<ChipModel>) {
        val enabledChips = allChips.filter { it.isEnable }
        if (enabledChips != chipsQueue) {
            chipsQueue.clear()
            chipsQueue.addAll(enabledChips.reversed().map { it.id })
        }
    }

    private fun isChipLimitReached(chip: ChipModel): Boolean {
        return chipsQueue.size >= maxChips && !chip.isEnable
    }

}