package com.sgcdeveloper.chips.model.chips

abstract class ChipModel(val isEnable: Boolean) {
    abstract fun <T : ChipModel> copy(isEnable: Boolean): T
    abstract override fun equals(other: Any?): Boolean
    abstract override fun hashCode(): Int
}