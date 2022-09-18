package com.sgcdeveloper.chips.controller

import com.sgcdeveloper.chips.controller.chipClickBehavior.ChipClickBehavior
import com.sgcdeveloper.chips.controller.chipClickBehavior.SingleChipClickBehavior
import com.sgcdeveloper.chips.model.ChipModel

open class ChipsController<T : ChipModel>(chips: List<T>) {

    private val onChipsChangedListeners: MutableList<OnChipsChangedListener> = mutableListOf()
    protected val chips: MutableList<T> = chips.toMutableList()

    var chipClickBehavior: ChipClickBehavior = SingleChipClickBehavior()
        set(value) {
            field = value
            updateChips(chipClickBehavior.hotInit(chips))
        }

    open fun onChipClick(chip: T) {
        chipClickBehavior.onChipClicked(chip, chips)
        notifyOnChipsChanged()
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

    fun getChipById(id: String): T? = chips.find { it.id == id }

    private fun notifyOnChipsChanged() {
        onChipsChangedListeners.forEach { listener ->
            listener.onChipsChanged(chips)
        }
    }

    private fun updateChips(newChips: List<T>) {
        chips.clear()
        chips.addAll(newChips)
    }
}