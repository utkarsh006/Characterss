package com.example.characters.presentation.favorites.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.characters.presentation.character_detail.components.AppText
import com.example.characters.presentation.character_detail.components.TextType
import com.example.characters.R

@Composable
fun NoFavoritesUi() {

    Image(
        painter = painterResource(id = R.drawable.ic_no_favs),
        contentDescription = "No Favorites Saved",
        modifier = Modifier.size(200.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    Spacer(modifier = Modifier.height(16.dp))

    AppText(
        text = "No Favorites Yet !!",
        type = TextType.BODY_BOLD,
        modifier = Modifier.padding(8.dp)
    )
}