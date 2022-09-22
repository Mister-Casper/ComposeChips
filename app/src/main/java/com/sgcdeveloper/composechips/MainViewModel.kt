package com.sgcdeveloper.composechips

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sgcdeveloper.chips.controller.ChipsController
import com.sgcdeveloper.chips.controller.chipClickBehavior.LimitedMultiChipsClickBehavior
import com.sgcdeveloper.chips.controller.chipClickBehavior.MultipleChipsClickBehavior
import com.sgcdeveloper.chips.controller.chipsLimit.MaxChipsLimit
import com.sgcdeveloper.chips.model.chips.TextChipModel
import com.sgcdeveloper.chips.model.chips.imageChip.ChipImage
import com.sgcdeveloper.chips.model.chips.imageChip.ImageChipModel
import com.sgcdeveloper.chips.model.toText

class MainViewModel : ViewModel() {

    private val chipsController = ChipsController(chips)
    private val chipsControllerMultiNoLimits = ChipsController(chips, MultipleChipsClickBehavior())
    private val chipsControllerMulti =
        ChipsController(chips, LimitedMultiChipsClickBehavior(MaxChipsLimit(2)))
    private val chipsControllerImages = ChipsController(imagePics)

    private val _singleActiveItemChips: MutableLiveData<List<TextChipModel>> =
        MutableLiveData(chipsController.getAllChips())
    val singleActiveItemChips: LiveData<List<TextChipModel>> = _singleActiveItemChips

    private val _multiNoLimitsItemChip: MutableLiveData<List<TextChipModel>> =
        MutableLiveData(chipsControllerMultiNoLimits.getAllChips())
    val multiNoLimitsItemChip: LiveData<List<TextChipModel>> = _multiNoLimitsItemChip

    private val _multiItemChip: MutableLiveData<List<TextChipModel>> =
        MutableLiveData(chipsControllerMulti.getAllChips())
    val multiItemChip: LiveData<List<TextChipModel>> = _multiItemChip

    private val _singleImageChip: MutableLiveData<List<ImageChipModel>> =
        MutableLiveData(chipsControllerImages.getAllChips())
    val singleImageChip: LiveData<List<ImageChipModel>> = _singleImageChip

    fun onSingleItemClick(chipModel: TextChipModel) {
        _singleActiveItemChips.value = chipsController.onChipClick(chipModel)
    }

    fun onMultiItemNoLimitClick(chipModel: TextChipModel) {
        _multiNoLimitsItemChip.value = chipsControllerMultiNoLimits.onChipClick(chipModel)
    }

    fun onMultiItemClick(chipModel: TextChipModel) {
        _multiItemChip.value = chipsControllerMulti.onChipClick(chipModel)
    }

    fun onSingleImageClick(chipModel: ImageChipModel) {
        _singleImageChip.value = chipsControllerImages.onChipClick(chipModel)
    }

    private companion object {
        private val chips = listOf(
            TextChipModel(isActive = false, text = "A".toText()),
            TextChipModel(isActive = false, text = "B".toText()),
            TextChipModel(isActive = false, text = "C".toText()),
            TextChipModel(isActive = false, text = "D".toText()),
            TextChipModel(isActive = false, text = "E".toText()),
            TextChipModel(isActive = false, text = "F".toText()),
            TextChipModel(isActive = false, text = "G".toText()),
        )

        private val imagePics = listOf(
            ImageChipModel(
                isEnable = false, leftImage = ChipImage(
                    imageRes = R.drawable.test,
                    enableTint = Color.White,
                    disableTint = Color.Red
                ), text = "IMAGE A".toText()
            ),
            ImageChipModel(
                isEnable = false, rightImage = ChipImage(
                    imageRes = R.drawable.test,
                    enableTint = Color.White,
                    disableTint = Color.Blue
                ), text = "IMAGE B".toText()
            ),
            ImageChipModel(
                isEnable = false, leftImage = ChipImage(
                    imageRes = R.drawable.test,
                    enableTint = Color.White,
                    disableTint = Color.Green
                ), text = "IMAGE C".toText()
            ),
        )
    }
}