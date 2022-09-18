package com.sgcdeveloper.composechips

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.sgcdeveloper.chips.model.TextChipModel
import com.sgcdeveloper.chips.ui.TextChipsRow
import com.sgcdeveloper.composechips.ui.theme.ComposeChipsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeChipsTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

                    TextChipsRow(
                        textChips = listOf(
                            TextChipModel(isActive = false, id = "A", text = "A"),
                            TextChipModel(isActive = false, id = "B", text = "B"),
                            TextChipModel(isActive = false, id = "C", text = "C")
                        ),
                        onClick = { chipText ->

                        }
                    )

                }
            }
        }
    }
}