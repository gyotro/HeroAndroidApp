package com.example.heroapp.presentation.screens.details


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.heroapp.domain.model.Hero
import com.example.heroapp.presentation.components.InfoBox
import com.example.heroapp.presentation.components.OrderedList
import com.example.heroapp.ui.theme.LARGE_PADDING
import com.example.heroapp.ui.theme.SMALL_PADDING
import com.example.heroapp.ui.theme.titleColor
import com.example.heroapp.util.Constants.BASE_URL
import com.example.heroapp.R as R

@Composable
fun DetailsScreen(
    navController: NavHostController,
    // appena clicchiamo sull'immagine per avere il dettaglio, sarà inizializzata la DetailsScreenViewModel
    detailsScreenViewModel: DetailsScreenViewModel = hiltViewModel()
)
{
    val selectedHero by detailsScreenViewModel.heroDetail.collectAsState()
    DetailsScreenContent(
        navController = navController,
        hero = selectedHero
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreenContent(
    navController: NavHostController,
    hero: Hero?
){
    //using Matherial3 Experimental API
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(initialValue = SheetValue.Expanded)
    )

    BottomSheetScaffold(
        //dimensione in minima estensione (Collapsed)
        sheetPeekHeight = 150.dp,
        scaffoldState = scaffoldState,
        sheetContent = {
            hero?.let { BottomSheetContent(selectedHero = it) }
        },
        content = {
            hero?.image?.let { hero ->
                BackgroundContent(
                    heroImage = hero,
 //                   imageFraction = ,
                    onCloseAction = { navController.popBackStack() }

                )
            }
        }
    )
}



@Composable
fun BottomSheetContent(
    selectedHero: Hero,
    infoBoxIconColor: Color = MaterialTheme.colorScheme.primary,
    sheetBackgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = androidx.compose.material.MaterialTheme.colors.titleColor
){
    Column(
        modifier = Modifier
            .background(sheetBackgroundColor)
            .padding(all = LARGE_PADDING)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = LARGE_PADDING)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "HeroLogo",
                modifier = Modifier
                    .size(32.dp)
                    .weight(2f), // prenderà il 20%% della row
                tint = contentColor
            )
            Text(
                modifier = Modifier.weight(8f), // prenderà l'80% della row
                text = selectedHero.name,
                color = contentColor,
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            InfoBox(
                icon = painterResource(id = R.drawable.electric_bolt),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.power}",
                smallText = "Power",
                textColor = contentColor
            )
            //Spacer(modifier = Modifier.padding(20.dp))
            InfoBox(
                icon = painterResource(id = R.drawable.calendar),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.month}",
                smallText = "Month",
                textColor = contentColor
            )
            //Spacer(modifier = Modifier.padding(20.dp))
            InfoBox(
                icon = painterResource(id = R.drawable.cake),
                iconColor = infoBoxIconColor,
                bigText = "${selectedHero.day}",
                smallText = "Birthday",
                textColor = contentColor
            )
        }
        Text(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
            text = "About",
            color = contentColor,
            fontSize = MaterialTheme.typography.labelLarge.fontSize,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .padding(bottom = 20.dp)
                .fillMaxWidth(),
            text = selectedHero.about,
            color = contentColor,
            fontSize = MaterialTheme.typography.bodySmall.fontSize,
            maxLines = 7
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OrderedList(
                title = "Family",
                items = selectedHero.family,
                textColor = contentColor
            )
            OrderedList(
                title = "Abilities",
                items = selectedHero.abilities,
                textColor = contentColor
            )
            OrderedList(
                title = "Nature types",
                items = selectedHero.natureTypes,
                textColor = contentColor
            )
        }
    }
}

@Composable
fun BackgroundContent(
    heroImage: String,
    imageFraction: Float = 1f,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    onCloseAction: () -> Unit
) {
    val imageUrl = "$BASE_URL$heroImage"
    Box(modifier = Modifier
        .fillMaxSize()
        .background(backgroundColor)) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "Hero_Image",
            contentScale = ContentScale.Crop,
            onLoading = { CircularProgressIndicator(
                color = Color.Blue,
                strokeWidth = 6.dp
            ) },
            modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = imageFraction).align(Alignment.TopStart)
        )
        IconButton(
            onClick = { onCloseAction },
            modifier = Modifier.fillMaxWidth().padding(SMALL_PADDING)
        ){
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "CloseIcon",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )


        }
    }
}

@Composable
@Preview
fun  BottomSheetContentPreview() {
    BottomSheetContent(selectedHero = Hero(
        id = 1,
        name = "Test1",
        image = "",
        about = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed eu felis at nisi euismod sollicitudin. Donec pellentesque fermentum ultricies. Nunc feugiat, sem sit amet eleifend venenatis, libero leo posuere ante, vitae interdum purus magna ut odio. Quisque congue eros et neque iaculis" ,
        rating = 4.4,
        power = 4,
        month = "12",
        day = "23",
        family = listOf("1","2", "3"),
        abilities = listOf("1","2", "3"),
        natureTypes = listOf("1","2", "3")
    ))
}