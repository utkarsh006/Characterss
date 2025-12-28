package com.example.characters.presentation.character_detail.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

enum class TextType {
    TITLE,
    SECTION_HEADER,
    BODY,
    BODY_BOLD,
    ERROR
}

@Composable
fun AppText(
    text: String,
    type: TextType,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null
) {
    val (style, fontWeight, color) = when (type) {
        TextType.TITLE -> Triple(
            MaterialTheme.typography.headlineMedium,
            FontWeight.Bold,
            MaterialTheme.colorScheme.onSurface
        )

        TextType.SECTION_HEADER -> Triple(
            MaterialTheme.typography.titleLarge,
            FontWeight.Bold,
            MaterialTheme.colorScheme.onSurface
        )

        TextType.BODY -> Triple(
            MaterialTheme.typography.bodyLarge,
            FontWeight.Normal,
            MaterialTheme.colorScheme.onSurface
        )

        TextType.BODY_BOLD -> Triple(
            MaterialTheme.typography.bodyLarge,
            FontWeight.Bold,
            MaterialTheme.colorScheme.onSurface
        )

        TextType.ERROR -> Triple(
            MaterialTheme.typography.bodyLarge,
            FontWeight.Normal,
            MaterialTheme.colorScheme.error
        )
    }

    Text(
        text = text,
        style = style,
        fontWeight = fontWeight,
        color = color,
        textAlign = textAlign,
        modifier = modifier
    )
}