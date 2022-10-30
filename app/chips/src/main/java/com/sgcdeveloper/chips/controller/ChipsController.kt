package com.sgcdeveloper.chips.controller

import com.sgcdeveloper.chips.controller.chipClickBehavior.ChipClickBehavior
import com.sgcdeveloper.chips.controller.chipClickBehavior.SingleChipClickBehavior
import com.sgcdeveloper.chips.model.chips.ChipModel

open class ChipsController<T : ChipModel>(
    chips: List<T>,
    private var chipClickBehavior: ChipClickBehavior = SingleChipClickBehavior()
) {

    private val onChipsChangedListeners: MutableList<OnChipsChangedListener> = mutableListOf()
    private var identifiedChips: List<T> = chips.getIdentifiedChips()

    open fun onChipClick(chipIndex: Int): List<T> {
        val updatedChips =
            chipClickBehavior.onChipClicked(identifiedChips[chipIndex], identifiedChips)
        updateChips(updatedChips)
        return identifiedChips
    }

    open fun onChipClick(chip: T): List<T> {
        val updatedChips = chipClickBehavior.onChipClicked(chip, identifiedChips)
        updateChips(updatedChips)
        return identifiedChips
    }

    fun resetChipClickBehavior(chipClickBehavior: ChipClickBehavior): List<T> {
        this.chipClickBehavior = chipClickBehavior
        val updatedChips = chipClickBehavior.hotInit(identifiedChips)
        updateChips(updatedChips)
        return updatedChips
    }

    fun addOnChipsChangedListener(listener: OnChipsChangedListener) {
        onChipsChangedListeners.add(listener)
    }

    fun removeOnChipsChangedListener(listener: OnChipsChangedListener) {
        onChipsChangedListeners.remove(listener)
    }

    fun getAllChips(): List<T> = identifiedChips.toList()

    fun getEnableChips(): List<T> = identifiedChips.filter { it.isEnable }

    fun getFirstEnableChips(): T? = identifiedChips.firstOrNull { it.isEnable }

    fun getDisableChips(): List<T> = identifiedChips.filter { !it.isEnable }

    fun setChips(chips: List<T>) {
        val updatedChips = chipClickBehavior.hotInit(chips.getIdentifiedChips())
        updateChips(updatedChips)
    }

    private fun updateChips(newChips: List<T>) {
        identifiedChips = newChips
        notifyOnChipsChanged()
    }

    private fun notifyOnChipsChanged() {
        onChipsChangedListeners.forEach { listener ->
            listener.onChipsChanged(identifiedChips)
        }
    }
}

fun <T : ChipModel> List<T>.getIdentifiedChips(): List<T> {
    return if (isAllChipsIdUnique(this)) {
        this.toMutableList()
    } else {
        this.mapIndexed { index, chip -> chip.copy(id = index.toString()) as T }
            .toMutableList()
    }
}

private fun <T : ChipModel> isAllChipsIdUnique(chips: List<T>): Boolean {
    val chipsId = chips.map { it.id }
    return chipsId.toSet().size == chips.size
}