package com.sgcdeveloper.chips.model.chips

abstract class ChipModel(val id: String = "", val isEnable: Boolean) {
    abstract fun <T : ChipModel> copy(id: String = this.id, isEnable: Boolean = this.isEnable): T

    override fun equals(other: Any?): Boolean {
        if (other is ChipModel) {
            return other.id == id && other.isEnable == isEnable
        }
        return false
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}