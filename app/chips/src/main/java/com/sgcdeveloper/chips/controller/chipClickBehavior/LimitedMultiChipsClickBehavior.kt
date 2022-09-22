package com.sgcdeveloper.chips.controller.chipClickBehavior

import com.sgcdeveloper.chips.controller.chipsLimit.MaxChipsLimit
import com.sgcdeveloper.chips.model.chips.ChipModel

class LimitedMultiChipsClickBehavior(private val maxChipsLimit: MaxChipsLimit? = null) : ChipClickBehavior {

    override fun <T : ChipModel> onChipClicked(chip: T, allChips: List<T>): List<T> {
        maxChipsLimit?.addChip(chip)
        val enabledChips = maxChipsLimit?.getEnabledChips() ?: emptyList()
        return allChips.map { item ->
            val enabledChip = item.copy(isEnable = true) as T
            val isEnable = enabledChips.contains(enabledChip)
            item.copy(isEnable = isEnable)
        }
    }

    override fun <T : ChipModel> hotInit(allChips: List<T>): List<T> {
        maxChipsLimit?.hotInit(allChips)
        return allChips
    }

}