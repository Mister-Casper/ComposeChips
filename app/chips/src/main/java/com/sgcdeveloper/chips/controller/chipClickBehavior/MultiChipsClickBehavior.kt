package com.sgcdeveloper.chips.controller.chipClickBehavior

import com.sgcdeveloper.chips.controller.chipsLimit.MaxChipsLimit
import com.sgcdeveloper.chips.model.chips.ChipModel

class MultiChipsClickBehavior(private val maxChipsLimit: MaxChipsLimit? = null) :
    ChipClickBehavior {

    override fun <T : ChipModel> onChipClicked(chip: T, allChips: List<T>): List<T> {
        if (maxChipsLimit == null) {
            return allChips.map { item ->
                if (item == chip) {
                    chip.copy(isEnable = !chip.isEnable)
                } else {
                    item
                }
            }
        }
        maxChipsLimit.addChip(chip)
        val enabledChips = maxChipsLimit.getEnabledChips<T>().map { it.id }
        return allChips.map { item ->
            item.copy(isEnable = enabledChips.contains(item.id))
        }
    }

    override fun <T : ChipModel> hotInit(allChips: List<T>): List<T> {
        return maxChipsLimit?.hotInit(allChips) ?: allChips
    }

}