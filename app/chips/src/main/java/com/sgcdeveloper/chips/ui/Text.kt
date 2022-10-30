package com.sgcdeveloper.chips.ui

import androidx.compose.material.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.sgcdeveloper.chips.model.AnnotatedText
import com.sgcdeveloper.chips.model.ResArgsText
import com.sgcdeveloper.chips.model.ResText
import com.sgcdeveloper.chips.model.StringText
import com.sgcdeveloper.chips.model.Text

@Composable
fun Text(
    text: Text,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    fontSize: TextUnit = 14.sp
) {
    val textValue = when (text) {
        is AnnotatedText -> text.annotatedString
        is ResArgsText -> stringResource(id = text.stringResId, formatArgs = text.args)
        is ResText -> stringResource(id = text.stringId)
        is StringText -> text.string
    }
    when (textValue) {
        is AnnotatedString -> {
            androidx.compose.material.Text(
                color = color,
                text = textValue,
                modifier = modifier,
                style = style,
                maxLines = maxLines,
                overflow = overflow,
                textAlign = textAlign,
                fontSize = fontSize
            )
        }

        is String -> {
            androidx.compose.material.Text(
                color = color,
                text = textValue,
                modifier = modifier,
                style = style,
                maxLines = maxLines,
                overflow = overflow,
                textAlign = textAlign,
                fontSize = fontSize
            )
        }
    }
}