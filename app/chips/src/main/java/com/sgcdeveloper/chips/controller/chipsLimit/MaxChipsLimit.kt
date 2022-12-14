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
            chipsQueue.remove(chip.copy(isEnable = false))
        } else {
            chipsQueue.addFirst(chip)
        }
    }

    override fun <T : ChipModel> getEnabledChips(): List<T> {
        return chipsQueue.toList() as List<T>
    }

    override fun <T : ChipModel> hotInit(allChips: List<T>): List<T> {
        val enabledChips = allChips.filter { it.isEnable }
        return if (enabledChips.map { it.id } != chipsQueue.map { it.id }) {
            val limitedEnableChips =
                enabledChips.subList(0, kotlin.math.min(maxChips, enabledChips.size)).reversed()
            chipsQueue.clear()
            chipsQueue.addAll(limitedEnableChips.map { it })
            allChips.map { it.copy(isEnable = chipsQueue.contains(it)) }
        } else {
            allChips
        }
    }

    private fun isChipLimitReached(chip: ChipModel): Boolean {
        return chipsQueue.size >= maxChips && !chip.isEnable
    }

}