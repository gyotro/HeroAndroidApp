package com.example.heroapp.presentation.common

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.heroapp.R
import com.example.heroapp.domain.model.Hero
import com.example.heroapp.navigation.Screen
import com.example.heroapp.presentation.components.RatingWidget
import com.example.heroapp.ui.theme.HERO_ITEM_HIGHT
import com.example.heroapp.ui.theme.MEDIUM_PADDING
import com.example.heroapp.ui.theme.SMALL_PADDING
import com.example.heroapp.ui.theme.TopAppBarBackgroundColor
import com.example.heroapp.util.Constants.BASE_URL
import kotlin.math.log


@Composable
fun ListContent(
    heroes: LazyPagingItems<Hero>,
    navHostController: NavHostController
) {
    Log.d("ListContent", heroes.loadState.toString())
    LazyColumn(
        // padding prima e dopo la Lazy Column
        contentPadding = PaddingValues(SMALL_PADDING),
        // padding fra gli elementi
        verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
    ) {
        // versione con il LazyPagingItems
        items(
            items = heroes,
            key = { item: Hero -> item.id }
        ) { item ->
            item?.let { hero ->
                HeroItem(hero, navHostController)
            }

        }
    }
}


@Composable
fun HeroItem(
    hero: Hero,
    navHostController: NavHostController
) {
    val url = "$BASE_URL${hero.image}"
    Box(
        modifier = Modifier.clickable {
            navHostController.navigate(Screen.Details.passHeroId(heroId = hero.id))
        }.height(HERO_ITEM_HIGHT),
        // verificare come si usa il content Alignment sul Box
        contentAlignment = Alignment.BottomStart
    )
    {
        Surface(shape = RoundedCornerShape(20.dp)) {
            // va visualizzata l'immagine dell'hero
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://example.com/image.jpg")
                    .crossfade(true)
                    //              .size(Size.ORIGINAL)
                    .build(),
                placeholder = painterResource(R.drawable.image_fill0_wght300_grad200_opsz48_white),
                error = painterResource(R.drawable.image_fill0_wght300_grad200_opsz48_white),
                contentDescription = stringResource(R.string.descriptionHero),
                contentScale = ContentScale.Crop,
                modifier = Modifier.clip(CircleShape).fillMaxSize()
            )
        }
        Surface(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(0.4f),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomEnd = 20.dp,
                bottomStart = 20.dp
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(all = MEDIUM_PADDING)
            ) {
                Text(
                    text = hero.name,
                    color = androidx.compose.material.MaterialTheme.colors.TopAppBarBackgroundColor,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis // con questa opzione se il testo troppo lungo si appunta
                )
                Text(
                    text = hero.about,
                    color = Color.White.copy(alpha = ContentAlpha.medium),
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis // con questa opzione se il testo troppo lungo si appunta
                )
                Row(
                    modifier = Modifier.padding(top = SMALL_PADDING),
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
                ) {
                    RatingWidget(
                        modifier = Modifier.padding(end = SMALL_PADDING),
                        rating = hero.rating
                    )
                    Text(
                        text = "(${hero.rating})",
                        textAlign = TextAlign.Center,
                        color = Color.White.copy(alpha = ContentAlpha.medium)
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun previewHeroItem() {
    HeroItem(
        hero = Hero(
            id = 2,
            name = "GokuSSJ",
            image = "",
            about = "Test Descriptionnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn",
            rating = 4.9,
            power = 4,
            month = "6",
            day = "17",
            family = emptyList(),
            abilities = emptyList(),
            natureTypes = emptyList()
        ), navHostController = rememberNavController()
    )
}

