package com.example.characters.presentation.character_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.characters.domain.model.CharacterDisplay

@Composable
fun CharacterListItem(
    character: CharacterDisplay,
    onItemClicked: (CharacterDisplay) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable { onItemClicked(character) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AsyncImage(
                model = character.image,
                contentDescription = character.name,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp)
            )

            Text(
                text = character.name,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(CenterVertically)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealListItemPreview() {
    CharacterListItem(
        CharacterDisplay(
            created = "2017-11-04T18:48:46.250Z",
            gender = "Male",
            id = 1,
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            name = "Rick Sanchez"
        ),
        onItemClicked = {}
    )

}