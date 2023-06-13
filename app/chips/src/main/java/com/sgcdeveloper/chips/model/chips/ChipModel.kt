package com.sgcdeveloper.chips.model.chips

abstract class ChipModel(val id: String = "", val isEnabled: Boolean) {
    abstract fun <T : ChipModel> copy(id: String = this.id, isEnabled: Boolean = this.isEnabled): T

    override fun equals(other: Any?): Boolean {
        if (other is ChipModel) {
            return other.id == id && other.isEnabled == isEnabled
        }
        return false
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
