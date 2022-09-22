package com.sgcdeveloper.chips.model

import androidx.annotation.StringRes
import androidx.compose.ui.text.AnnotatedString

sealed interface Text

@JvmInline
value class ResText(val stringId: Int) : Text

@JvmInline
value class StringText(val string: String) : Text

class AnnotatedText(val annotatedString: AnnotatedString) : Text

class ResArgsText(@StringRes val stringResId: Int, vararg val args: Any) : Text


fun String.toText() = StringText(this)

fun Int.toText() = ResText(this)

fun AnnotatedString.toText() = AnnotatedText(this)