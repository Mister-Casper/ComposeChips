package com.sgcdeveloper.composechips

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import com.sgcdeveloper.chips.controller.ChipsController
import com.sgcdeveloper.chips.model.TextChipModel
import com.sgcdeveloper.chips.ui.TextChipsRow
import com.sgcdeveloper.composechips.ui.theme.ComposeChipsTheme
import kotlin.reflect.KProperty

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeChipsTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column(Modifier.fillMaxWidth()) {

                        val textChips = mainViewModel.singleActiveItemChips.observeAsState()
                        val textMultiNoLimitChips = mainViewModel.multiNoLimitsItemClick.observeAsState()
                        val textMultiLimitChips = mainViewModel.multiItemClick.observeAsState()

                        TextChipsRow(
                            textChips = textChips.value ?: emptyList(),
                            onClick = { chipText ->
                                mainViewModel.onSingleItemClick(chipText)
                            }
                        )
                        TextChipsRow(
                            textChips = textMultiNoLimitChips.value ?: emptyList(),
                            onClick = { chipText ->
                                mainViewModel.onMultiItemNoLimitClick(chipText)
                            }
                        )
                        TextChipsRow(
                            textChips = textMultiLimitChips.value ?: emptyList(),
                            onClick = { chipText ->
                                mainViewModel.onMultiItemClick(chipText)
                            }
                        )
                    }
                }
            }
        }
    }
}