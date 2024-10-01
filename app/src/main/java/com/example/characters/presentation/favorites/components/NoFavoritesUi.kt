package com.example.characters.presentation.favorites.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.characters.presentation.character_list.components.TextComponent
import com.example.crypto.R

@Composable
fun NoFavoritesUi() {

    Image(
        painter = painterResource(id = R.drawable.ic_no_favs),
        contentDescription = "No Favorites Saved",
        modifier = Modifier.size(200.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    Spacer(modifier = Modifier.height(16.dp))

    TextComponent(
        text = "No Favorites Yet !!",
        fontWeight = FontWeight.SemiBold,
        fontSize = 18f,
        color = Color.Black,
        padding = 8.dp
    )
}