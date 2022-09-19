package com.sgcdeveloper.composechips

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sgcdeveloper.chips.controller.ChipsController
import com.sgcdeveloper.chips.controller.chipClickBehavior.LimitedMultiChipsClickBehavior
import com.sgcdeveloper.chips.controller.chipClickBehavior.MultipleChipsClickBehavior
import com.sgcdeveloper.chips.controller.chipsLimit.MaxChipsLimit
import com.sgcdeveloper.chips.model.ImagePosition
import com.sgcdeveloper.chips.model.chips.TextChipModel
import com.sgcdeveloper.chips.model.chips.imageChip.ChipImage
import com.sgcdeveloper.chips.model.chips.imageChip.ImageChipModel

class MainViewModel : ViewModel() {


    private val _singleActiveItemChips: MutableLiveData<List<TextChipModel>> = MutableLiveData(chips)
    val singleActiveItemChips: LiveData<List<TextChipModel>> = _singleActiveItemChips

    private val _multiNoLimitsItemChip: MutableLiveData<List<TextChipModel>> = MutableLiveData(chips)
    val multiNoLimitsItemChip: LiveData<List<TextChipModel>> = _multiNoLimitsItemChip

    private val _multiItemChip: MutableLiveData<List<TextChipModel>> = MutableLiveData(chips)
    val multiItemChip: LiveData<List<TextChipModel>> = _multiItemChip

    private val _singleImageChip: MutableLiveData<List<ImageChipModel>> = MutableLiveData(imagePics)
    val singleImageChip: LiveData<List<ImageChipModel>> = _singleImageChip

    private val chipsController = ChipsController(chips)
    private val chipsControllerMultiNoLimits = ChipsController(chips, MultipleChipsClickBehavior())
    private val chipsControllerMulti = ChipsController(chips, LimitedMultiChipsClickBehavior(MaxChipsLimit(2)))
    private val chipsControllerImages = ChipsController(imagePics)

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
            TextChipModel(isActive = false, text = "A"),
            TextChipModel(isActive = false, text = "B"),
            TextChipModel(isActive = false, text = "C"),
            TextChipModel(isActive = false, text = "D"),
            TextChipModel(isActive = false, text = "E"),
            TextChipModel(isActive = false, text = "F"),
            TextChipModel(isActive = false, text = "G"),
        )

        private val imagePics = listOf(
            ImageChipModel(
                isEnable = false, image = ChipImage(
                    chipImagePosition = ImagePosition.LEFT,
                    imageRes = R.drawable.test,
                    enableTint = Color.White,
                    disableTint = Color.Red
                ), text = "IMAGE A"
            ),
            ImageChipModel(
                isEnable = false, image = ChipImage(
                    chipImagePosition = ImagePosition.RIGHT,
                    imageRes = R.drawable.test,
                    enableTint = Color.White,
                    disableTint = Color.Blue
                ), text = "IMAGE B"
            ),
            ImageChipModel(
                isEnable = false, image = ChipImage(
                    chipImagePosition = ImagePosition.RIGHT,
                    imageRes = R.drawable.test,
                    enableTint = Color.White,
                    disableTint = Color.Green
                ), text = "IMAGE C"
            ),
        )
    }
}