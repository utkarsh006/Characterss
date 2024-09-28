package com.example.characters.presentation.character_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.characters.presentation.character_list.CharacterListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchComponent(
    searchText: String,
    isSearching: Boolean,
    onSearchTextChange: (String) -> Unit,
    viewModel: CharacterListViewModel = hiltViewModel()
) {
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
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                },
                // Show the trailing icon only when searchText is not empty
                trailingIcon = if (searchText.isNotEmpty()) {
                    {
                        IconButton(
                            onClick = { viewModel.onSearchTextChange("") }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear Icon"
                            )
                        }
                    }
                } else null,
                colors = TextFieldDefaults.colors(Color.Black)
            )
        },
        expanded = isExpanded,
        onExpandedChange = { isExpanded = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(10.dp),
        content = {}
    )
}