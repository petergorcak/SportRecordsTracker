package com.gorcak.sportperformancetracker.core.presentation.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle


class LeadingZeroVisualTransformation(val placeholderColor: Color) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val originalText = text.text
        val number = originalText.toIntOrNull() ?: 0

        val annotatedString = buildAnnotatedString {
            if(number == 0){
                withStyle(style = SpanStyle(color = placeholderColor.copy(alpha = 0.3f))) {
                    append("00")
                }
            }   else {
                if(number < 10) {
                    withStyle(style = SpanStyle(color =placeholderColor.copy(alpha = 0.3f))) {
                        append("0")
                    }
                }
                append("$number")
            }
        }
        return TransformedText(
            annotatedString,
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int {
                    return if(originalText.isEmpty()){
                        2
                    } else {
                        if (number < 10) offset + 1 else offset
                    }
                }

                override fun transformedToOriginal(offset: Int): Int {
                    return if(originalText.isEmpty()){
                        0
                    } else {
                        if (number < 10) (offset - 1).coerceAtLeast(0) else offset
                    }
                }
            }
        )
    }
}