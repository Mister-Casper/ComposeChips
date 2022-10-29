package com.sgcdeveloper.composechips

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.sgcdeveloper.chips.ui.ImageChipsRow
import com.sgcdeveloper.chips.ui.TextChipsGrid
import com.sgcdeveloper.chips.ui.TextChipsRow
import com.sgcdeveloper.composechips.ui.theme.ComposeChipsTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeChipsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val textChips =
                        mainViewModel.singleActiveItemChips.observeAsState(emptyList())
                    val textMultiNoLimitChips =
                        mainViewModel.multiNoLimitsItemChip.observeAsState(emptyList())
                    val textMultiLimitChips =
                        mainViewModel.multiItemChip.observeAsState(emptyList())
                    val imageSingleChip =
                        mainViewModel.singleImageChip.observeAsState(emptyList())
                    val gridChips = mainViewModel.gridChips.observeAsState(emptyList())
                    val controllerBehaviorChips =
                        mainViewModel.controllerBehaviorChips.observeAsState(emptyList())
                    val anyBehaviorChips =
                        mainViewModel.anyBehaviorChips.observeAsState(emptyList())

                    LazyColumn(Modifier.fillMaxWidth()) {
                        item {
                            TextChipsRow(
                                textChips = textChips.value,
                                onClick = { chipText ->
                                    mainViewModel.onSingleItemClick(chipText)
                                }
                            )
                            TextChipsRow(
                                textChips = textMultiNoLimitChips.value,
                                onClick = { chipText ->
                                    mainViewModel.onMultiItemNoLimitClick(chipText)
                                }
                            )
                            TextChipsRow(
                                textChips = textMultiLimitChips.value,
                                onClick = { chipText ->
                                    mainViewModel.onMultiItemClick(chipText)
                                }
                            )
                            ImageChipsRow(
                                imageChips = imageSingleChip.value,
                                onClick = { chipText ->
                                    mainViewModel.onSingleImageClick(chipText)
                                }
                            )
                            TextChipsGrid(chips = gridChips.value, onClick = { chipText ->
                                mainViewModel.onGridItemClick(chipText)
                            })
                            TextChipsRow(
                                textChips = textMultiLimitChips.value,
                                onClick = { chipText ->
                                    mainViewModel.onMultiItemClick(chipText)
                                }
                            )
                            TextChipsRow(
                                textChips = controllerBehaviorChips.value,
                                onClick = { chipText ->
                                    mainViewModel.onControllerBehaviorChipsClick(chipText)
                                }
                            )
                            TextChipsRow(
                                textChips = anyBehaviorChips.value,
                                onClick = { chipText ->
                                    mainViewModel.onAnyBehaviorChipsClick(chipText)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}