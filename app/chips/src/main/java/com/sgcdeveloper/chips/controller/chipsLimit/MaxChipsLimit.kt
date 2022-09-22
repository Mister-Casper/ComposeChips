package com.sgcdeveloper.chips.controller.chipsLimit

import com.sgcdeveloper.chips.model.chips.ChipModel

open class MaxChipsLimit(
    private val maxChips: Int = Int.MAX_VALUE
) : ChipsLimit {

    private val chipsQueue = ArrayDeque<ChipModel>()

    override fun <T : ChipModel> addChip(chip: T) {
        if (isChipLimitReached(chip)) {
            chipsQueue.removeLast()
        }

        if (chip.isEnable) {
            chipsQueue.remove(chip)
        } else {
            chipsQueue.addFirst(chip.copy(isEnable = true))
        }
    }

    override fun <T : ChipModel> getEnabledChips(): List<T> {
        return chipsQueue.toList() as List<T>
    }

    override fun <T : ChipModel> hotInit(allChips: List<T>) {
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