package com.sgcdeveloper.composechips

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sgcdeveloper.chips.controller.ChipsController
import com.sgcdeveloper.chips.controller.chipClickBehavior.MultiChipsClickBehavior
import com.sgcdeveloper.chips.controller.chipClickBehavior.SingleChipClickBehavior
import com.sgcdeveloper.chips.controller.chipsLimit.MaxChipsLimit
import com.sgcdeveloper.chips.model.chips.TextChipModel
import com.sgcdeveloper.chips.model.chips.imageChip.ChipImage
import com.sgcdeveloper.chips.model.chips.imageChip.ImageChipModel
import com.sgcdeveloper.chips.model.toText

class MainViewModel : ViewModel() {

    private val chipsController = ChipsController(chips)
    private val chipsControllerMultiNoLimits = ChipsController(chips, MultiChipsClickBehavior())
    private val chipsControllerMulti =
        ChipsController(chips, MultiChipsClickBehavior(MaxChipsLimit(2)))
    private val chipsControllerImages = ChipsController(imagePics)
    private val gridChipsController = ChipsController(chips)
    private val behaviorChipsController = ChipsController(behaviorChips)
    private val anyBehaviorChipsController = ChipsController(chips)

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

    private val _gridChips: MutableLiveData<List<TextChipModel>> =
        MutableLiveData(gridChipsController.getAllChips())
    val gridChips: LiveData<List<TextChipModel>> = _gridChips

    private val _controllerBehaviorChips: MutableLiveData<List<TextChipModel>> =
        MutableLiveData(behaviorChipsController.getAllChips())
    val controllerBehaviorChips: LiveData<List<TextChipModel>> = _controllerBehaviorChips

    private val _anyBehaviorChips: MutableLiveData<List<TextChipModel>> =
        MutableLiveData(anyBehaviorChipsController.getAllChips())
    val anyBehaviorChips: LiveData<List<TextChipModel>> = _anyBehaviorChips

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

    fun onGridItemClick(chipModel: TextChipModel) {
        _gridChips.value = gridChipsController.onChipClick(chipModel)
    }

    fun onControllerBehaviorChipsClick(chipModel: TextChipModel) {
        when (chipModel.id) {
            "1" -> {
                _anyBehaviorChips.value =
                    anyBehaviorChipsController.resetChipClickBehavior(SingleChipClickBehavior())
            }
            "2" -> {
                _anyBehaviorChips.value =
                    anyBehaviorChipsController.resetChipClickBehavior(MultiChipsClickBehavior())
            }
            "3" -> {
                _anyBehaviorChips.value =
                    anyBehaviorChipsController.resetChipClickBehavior(MultiChipsClickBehavior(MaxChipsLimit(2)))
            }
        }
        _controllerBehaviorChips.value = behaviorChipsController.onChipClick(chipModel)
    }

    fun onAnyBehaviorChipsClick(chipModel: TextChipModel) {
        _anyBehaviorChips.value = anyBehaviorChipsController.onChipClick(chipModel)
    }

    private companion object {
        private val behaviorChips = listOf(
            TextChipModel(isEnable = true, text = "Single".toText(), id = "1"),
            TextChipModel(isEnable = false, text = "Multi".toText(), id = "2"),
            TextChipModel(isEnable = false, text = "Multi with limit".toText(), id = "3"),
        )
        private val chips = listOf(
            TextChipModel(isEnable = false, text = "Chip 1".toText()),
            TextChipModel(isEnable = false, text = "Chip 2".toText()),
            TextChipModel(isEnable = false, text = "Chip 3".toText()),
            TextChipModel(isEnable = false, text = "Chip 4".toText()),
            TextChipModel(isEnable = false, text = "Chip 5".toText()),
            TextChipModel(isEnable = false, text = "Chip 6".toText()),
            TextChipModel(isEnable = false, text = "Chip 7".toText()),
            TextChipModel(isEnable = false, text = "Chip 8".toText()),
            TextChipModel(isEnable = false, text = "Chip 9".toText()),
            TextChipModel(isEnable = false, text = "Chip 10".toText()),
            TextChipModel(isEnable = false, text = "Chip 11".toText()),
            TextChipModel(isEnable = false, text = "Chip 12".toText()),
            TextChipModel(isEnable = false, text = "Chip 13".toText()),
            TextChipModel(isEnable = false, text = "Chip 14".toText()),
            TextChipModel(isEnable = false, text = "Chip 15".toText()),
            TextChipModel(isEnable = false, text = "Chip 16".toText()),
            TextChipModel(isEnable = false, text = "Chip 17".toText()),
            TextChipModel(isEnable = false, text = "Chip 18".toText()),
            TextChipModel(isEnable = false, text = "Chip 19".toText()),
            TextChipModel(isEnable = false, text = "Chip 20".toText()),
            TextChipModel(isEnable = false, text = "Chip 21".toText()),
            TextChipModel(isEnable = false, text = "Chip 22".toText()),
            TextChipModel(isEnable = false, text = "Chip 23".toText()),
            TextChipModel(isEnable = false, text = "Chip 24".toText()),
            TextChipModel(isEnable = false, text = "Chip 25".toText()),
            TextChipModel(isEnable = false, text = "Chip 26".toText()),
            TextChipModel(isEnable = false, text = "Chip 27".toText()),
            TextChipModel(isEnable = false, text = "Chip 28".toText()),
            TextChipModel(isEnable = false, text = "Chip 29".toText()),
            TextChipModel(isEnable = false, text = "Chip 30".toText()),
            TextChipModel(isEnable = false, text = "Chip 31".toText()),
            TextChipModel(isEnable = false, text = "Chip 32".toText()),
            TextChipModel(isEnable = false, text = "Chip 33".toText()),
            TextChipModel(isEnable = false, text = "Chip 34".toText()),
            TextChipModel(isEnable = false, text = "Chip 35".toText()),
            TextChipModel(isEnable = false, text = "Chip 36".toText()),
            TextChipModel(isEnable = false, text = "Chip 37".toText()),
            TextChipModel(isEnable = false, text = "Chip 38".toText()),
            TextChipModel(isEnable = false, text = "Chip 39".toText()),
            TextChipModel(isEnable = false, text = "Chip 40".toText()),
            TextChipModel(isEnable = false, text = "Chip 41".toText()),
            TextChipModel(isEnable = false, text = "Chip 42".toText()),
            TextChipModel(isEnable = false, text = "Chip 43".toText()),
            TextChipModel(isEnable = false, text = "Chip 44".toText()),
            TextChipModel(isEnable = false, text = "Chip 45".toText()),
            TextChipModel(isEnable = false, text = "Chip 46".toText()),
            TextChipModel(isEnable = false, text = "Chip 47".toText()),
            TextChipModel(isEnable = false, text = "Chip 48".toText()),
            TextChipModel(isEnable = false, text = "Chip 49".toText()),
            TextChipModel(isEnable = false, text = "Chip 50".toText()),
            TextChipModel(isEnable = false, text = "Chip 51".toText()),
            TextChipModel(isEnable = false, text = "Chip 52".toText()),
            TextChipModel(isEnable = false, text = "Chip 53".toText()),
            TextChipModel(isEnable = false, text = "Chip 54".toText()),
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