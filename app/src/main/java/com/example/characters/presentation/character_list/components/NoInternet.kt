package com.example.characters.presentation.character_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.crypto.R

@Composable
fun NoInternet(isRetrying: Boolean, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_no_internet),
            contentDescription = "No Internet Connection !!",
            modifier = Modifier.size(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextComponent(
            text = "No Internet Connection !!",
            fontWeight = FontWeight.SemiBold,
            fontSize = 14f,
            color = Color.Black,
            padding = 8.dp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onRetry,
            enabled = !isRetrying // Disable button when retry is in progress
        ) {
            if (isRetrying) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp)
                        .padding(4.dp)
                )
            } else {
                Text(text = "Retry")
            }
        }
    }
}
