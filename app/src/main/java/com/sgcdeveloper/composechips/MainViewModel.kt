package com.sgcdeveloper.composechips

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sgcdeveloper.chips.controller.ChipsController
import com.sgcdeveloper.chips.controller.chipClickBehavior.MultipleChipsClickBehavior
import com.sgcdeveloper.chips.controller.chipsLimit.MaxChipsLimit
import com.sgcdeveloper.chips.model.TextChipModel

class MainViewModel : ViewModel() {

    val chips = listOf(
        TextChipModel(isActive = false, id = "A", text = "A"),
        TextChipModel(isActive = false, id = "B", text = "B"),
        TextChipModel(isActive = false, id = "C", text = "C"),
        TextChipModel(isActive = false, id = "D", text = "D"),
        TextChipModel(isActive = false, id = "E", text = "E"),
        TextChipModel(isActive = false, id = "F", text = "F"),
    )

    private val _singleActiveItemChips: MutableLiveData<List<TextChipModel>> = MutableLiveData(chips)
    val singleActiveItemChips: LiveData<List<TextChipModel>> = _singleActiveItemChips

    private val _multiNoLimitsItemClick: MutableLiveData<List<TextChipModel>> = MutableLiveData(chips)
    val multiNoLimitsItemClick: LiveData<List<TextChipModel>> = _multiNoLimitsItemClick

    private val _multiItemClick: MutableLiveData<List<TextChipModel>> = MutableLiveData(chips)
    val multiItemClick: LiveData<List<TextChipModel>> = _multiItemClick

    private val chipsController = ChipsController(chips)
    private val chipsControllerMultiNoLimits = ChipsController(chips)
    private val chipsControllerMulti = ChipsController(chips)

    init {
        chipsControllerMultiNoLimits.chipClickBehavior = MultipleChipsClickBehavior()
        chipsControllerMulti.chipClickBehavior = MultipleChipsClickBehavior(MaxChipsLimit(2))
    }

    fun onSingleItemClick(chipModel: TextChipModel) {
        _singleActiveItemChips.value = chipsController.onChipClick(chipModel).toList()
    }

    fun onMultiItemNoLimitClick(chipModel: TextChipModel) {
        _multiNoLimitsItemClick.value = chipsControllerMultiNoLimits.onChipClick(chipModel).toList()
    }

    fun onMultiItemClick(chipModel: TextChipModel) {
        _multiItemClick.value = chipsControllerMulti.onChipClick(chipModel).toList()
    }
}