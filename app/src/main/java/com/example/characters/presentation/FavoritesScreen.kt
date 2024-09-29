package com.example.characters.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.characters.presentation.character_list.components.TextComponent
import com.example.crypto.R


@Composable
fun FavoritesScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NoFavoritesUi()
    }
}

@Composable
fun NoFavoritesUi(modifier: Modifier = Modifier) {

    Image(
        painter = painterResource(id = R.drawable.ic_no_favs),
        contentDescription = "No Favorites Saved",
        modifier = Modifier.size(200.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    TextComponent(
        text = "No Favorites Yet !!",
        fontWeight = FontWeight.SemiBold,
        fontSize = 18f,
        color = Color.Black,
        padding = 8.dp
    )
}