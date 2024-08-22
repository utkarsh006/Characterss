package com.example.characters.presentation.character_detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.characters.common.Utility.formatCreatedDate
import com.example.characters.domain.model.CharacterDisplay
import com.example.characters.presentation.character_list.components.CharacterListItem
import com.example.characters.presentation.character_list.components.TextComponent

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CharacterDetailScreen(
    character: CharacterDisplay
) {
    Column(modifier = Modifier.fillMaxSize()) {
        CharacterListItem(character = character, onItemClicked = {})

        Spacer(Modifier.height(4.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextComponent(
                text = "ID: ${character.id}",
                fontWeight = FontWeight.Normal,
                fontSize = 10f,
                color = Color.Magenta,
                padding = 8.dp
            )

            TextComponent(
                text = "Gender: ${character.gender}",
                fontWeight = FontWeight.Normal,
                fontSize = 10f,
                color = Color.Magenta,
                padding = 8.dp
            )

            TextComponent(
                text = "Created: ${formatCreatedDate(character.created)}",
                fontWeight = FontWeight.Normal,
                fontSize = 10f,
                color = Color.Magenta,
                padding = 8.dp
            )

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun MealDetailScreenUIPreview() {
    CharacterDetailScreen(
        character = CharacterDisplay(
            created = "2017-11-04T18:48:46.250Z",
            gender = "Male",
            id = 1,
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            name = "Rick Sanchez"
        )
    )
}
