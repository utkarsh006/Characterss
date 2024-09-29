package com.example.characters.presentation.character_detail

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.characters.domain.model.CharacterDisplay
import com.example.characters.presentation.character_list.components.CharacterListItem
import com.example.characters.presentation.character_list.components.TextComponent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CharacterDetailScreen(
    context: Context,
    character: CharacterDisplay
) {
    Column(modifier = Modifier.fillMaxSize()) {
        CharacterListItem(context, character = character, onItemClicked = {})

        Spacer(Modifier.height(4.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextComponent(
                text = "ID: ${character.id}",
                fontWeight = FontWeight.Normal,
                fontSize = 14f,
                color = Color.Black,
                padding = 8.dp
            )

            TextComponent(
                text = "Status: ${character.status}",
                fontWeight = FontWeight.Normal,
                fontSize = 14f,
                color = Color.Black,
                padding = 8.dp
            )

            TextComponent(
                text = "Species: ${character.species}",
                fontWeight = FontWeight.Normal,
                fontSize = 14f,
                color = Color.Black,
                padding = 8.dp
            )

            TextComponent(
                text = "Gender: ${character.gender}",
                fontWeight = FontWeight.Normal,
                fontSize = 14f,
                color = Color.Black,
                padding = 8.dp
            )

        }
    }
}
