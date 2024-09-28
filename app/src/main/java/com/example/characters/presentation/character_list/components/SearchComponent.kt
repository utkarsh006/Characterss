package com.example.characters.presentation.character_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComponent(
    searchText: String,
    isSearching: Boolean,
    onSearchTextChange: (String) -> Unit
) {
    // Manage the expanded state of the search bar
    var isExpanded by remember { mutableStateOf(isSearching) }

    SearchBar(
        inputField = {
            TextField(
                value = searchText,
                onValueChange = { onSearchTextChange(it) },
                placeholder = {
                    Text(
                        text = "Search by name",
                        style = TextStyle(
                            color = Color.DarkGray,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        )
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                )
            )
        },
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(10.dp),
        content = {
            // Optional additional content can go here
        }
    )
}
