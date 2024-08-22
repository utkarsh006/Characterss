package com.example.characters.presentation.character_list.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

@Composable
fun TextComponent(
    text: String,
    fontWeight: FontWeight,
    fontSize: Float,
    color: Color,
    padding: Dp
) {
    Text(
        text = text,
        modifier = Modifier.padding(padding),
        style = TextStyle(
            fontWeight = fontWeight,
            fontSize = fontSize.sp,
            color = color,
            fontFamily = FontFamily.SansSerif
        )
    )
}