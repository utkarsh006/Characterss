package com.example.characters.presentation.favorites.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.characters.domain.model.CharacterDisplay
import com.example.characters.presentation.character_list.components.TextComponent


@Composable
fun FavoriteCharacterItem(character: CharacterDisplay) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /* Handle click event */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = character.image,
            contentDescription = character.name,
            modifier = Modifier
                .weight(1f)
                .height(150.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        TextComponent(
            text = character.name,
            fontWeight = FontWeight.Normal,
            fontSize = 12f,
            color = Color.Black,
            padding = 8.dp
        )

    }
}