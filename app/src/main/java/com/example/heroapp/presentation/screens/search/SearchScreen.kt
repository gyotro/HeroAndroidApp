package com.example.heroapp.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.ui.text.input.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AppBarDefaults
import androidx.compose.material.ContentAlpha
//import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.heroapp.presentation.common.ListContent
import dagger.hilt.android.lifecycle.HiltViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    navHostController: NavHostController,
    searchScreenViewModel: SearchScreenViewModel = hiltViewModel()
) {
    val searchQuery = searchScreenViewModel.searchQuery
    // questo sarà il content del nostro Scaffold
    val heroes = searchScreenViewModel.searchedHeroes.collectAsLazyPagingItems()
    Scaffold(
        topBar = { SearchTopBar (
            text = searchQuery,
            onTextChange = { searchScreenViewModel.updateSearchValue(it) },
            onSearchClicked = { searchScreenViewModel.searchHero(query = it) },
            onCloseClicked = {
                // si elimina lo screen attuale (il search) e si ritorna quello precedente (home)
                navHostController.popBackStack()
            }
        ) }
    ){
        // Displaying Searched Heroes (same Composable as Displaying the list of all heroes)
        ListContent(heroes = heroes, navHostController = navHostController)
    }
}

@Composable
fun SearchTopBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    SearchWidget(
        text = text,
        onTextChange = onTextChange,
        onSearchClicked = onSearchClicked,
        onCloseClicked = onCloseClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchWidget(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        tonalElevation = AppBarDefaults.TopAppBarElevation,
        //color = MaterialTheme.colors.TopAppBarBackgroundColor
        color = MaterialTheme.colorScheme.primary
    ) {
        TextField(
            value = text,
            onValueChange = { onTextChange(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                // il testo sarà meno marcato
                Text(
                    modifier = Modifier.alpha(ContentAlpha.medium),
                    text = "Search Here!",
                    color = Color.White
                )
            },
            textStyle = TextStyle(color = Color.White),
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedLabelColor = Color.Transparent,
                containerColor = Color.Blue,
                cursorColor = Color.White
            ),
            leadingIcon = {
                IconButton(
                    // sarà un po' sbiadita
                    modifier = Modifier.alpha(alpha = ContentAlpha.medium),
                    onClick = {  }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "searchIcon",
                        tint = MaterialTheme.colorScheme.contentColorFor(Color.White)
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (text.isNotEmpty())
                            onTextChange("") else onCloseClicked()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = "closeIcon",
                        tint = MaterialTheme.colorScheme.contentColorFor(Color.White)
                    )
                }
            },
            // queste due righe di codice fanno sì che nella tastiera che compare in basso ci
            // sia l'icona della lente x fare ricerca. Nella action si specifica l'azione da compiere quando
            // si spinge il bottone della ricerca
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClicked(text) }),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SearchWidgetPreview(){
    SearchWidget(
        text = "",
        onTextChange = {},
        onSearchClicked = {},
        onCloseClicked = {}
    )
}