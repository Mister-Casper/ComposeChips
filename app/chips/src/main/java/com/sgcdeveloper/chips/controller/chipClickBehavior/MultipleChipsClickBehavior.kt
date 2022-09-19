package com.sgcdeveloper.chips.controller.chipClickBehavior

import com.sgcdeveloper.chips.controller.chipsLimit.MaxChipsLimit
import com.sgcdeveloper.chips.model.ChipModel

class MultipleChipsClickBehavior(private val maxChipsLimit: MaxChipsLimit? = null) : ChipClickBehavior {

    override fun <T : ChipModel> onChipClicked(chip: T, allChips: List<T>): List<T> {
        maxChipsLimit?.addChip(chip)
        val enabledChips = maxChipsLimit?.getEnabledChips()
        return if (enabledChips != null) {
            allChips.map { item ->
                val isEnable = enabledChips.contains(item.id)
                item.copy(isEnable = isEnable)
            }
        } else {
            allChips.map { item ->
                if (item.id == chip.id) {
                    chip.copy(isEnable = !chip.isEnable)
                } else {
                    item
                }
            }
        }
    }

    override fun <T : ChipModel> hotInit(allChips: List<T>): List<T> {
        maxChipsLimit?.hotInit(allChips)
        return allChips.toList()
    }

}