package com.sgcdeveloper.chips.controller

import com.sgcdeveloper.chips.controller.chipClickBehavior.ChipClickBehavior
import com.sgcdeveloper.chips.controller.chipClickBehavior.SingleChipClickBehavior
import com.sgcdeveloper.chips.model.chips.ChipModel

open class ChipsController<T : ChipModel>(
    chips: List<T>,
    private var chipClickBehavior: ChipClickBehavior = SingleChipClickBehavior(chips)
) {

    private val onChipsChangedListeners: MutableList<OnChipsChangedListener> = mutableListOf()
    protected val chips: MutableList<T> = chips.toMutableList()

    fun resetChipClickBehavior(chipClickBehavior: ChipClickBehavior) {
        this.chipClickBehavior = chipClickBehavior
        val updatedChips = chipClickBehavior.hotInit(chips)
        updateChips(updatedChips)
    }

    open fun onChipClick(chip: T): List<T> {
        val updatedChips = chipClickBehavior.onChipClicked(chip, chips)
        updateChips(updatedChips)
        return chips.toList()
    }

    fun addOnChipsChangedListener(listener: OnChipsChangedListener) {
        onChipsChangedListeners.add(listener)
    }

    fun removeOnChipsChangedListener(listener: OnChipsChangedListener) {
        onChipsChangedListeners.remove(listener)
    }

    fun getAllChips(): List<T> = chips.toList()

    fun getEnableChips(): List<T> = chips.filter { it.isEnable }

    fun getDisableChips(): List<T> = chips.filter { !it.isEnable }

    private fun updateChips(newChips: List<T>) {
        chips.clear()
        chips.addAll(newChips)
        notifyOnChipsChanged()
    }

    private fun notifyOnChipsChanged() {
        onChipsChangedListeners.forEach { listener ->
            listener.onChipsChanged(chips)
        }
    }
}