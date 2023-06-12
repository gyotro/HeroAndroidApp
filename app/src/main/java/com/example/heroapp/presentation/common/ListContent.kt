package com.example.heroapp.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.heroapp.R
import com.example.heroapp.domain.model.Hero
import com.example.heroapp.navigation.Screen
import com.example.heroapp.ui.theme.HERO_ITEM_HIGHT
import com.example.heroapp.ui.theme.MEDIUM_PADDING
import com.example.heroapp.ui.theme.TopAppBarBackgroundColor
import com.example.heroapp.util.Constants
import com.example.heroapp.util.Constants.BASE_URL


@Composable
fun ListContent(heroes: LazyPagingItems<Hero>,
                navHostController: NavHostController) {


}

@Composable
fun HeroItem(
    hero: Hero,
    navHostController: NavHostController
){
    val url = "$BASE_URL${hero.image}"
    Box(modifier = Modifier.
        clickable {
            navHostController.navigate(Screen.Details.passHeroId(heroId = hero.id))
        }.height(HERO_ITEM_HIGHT)
    )
    {
        Surface(shape = RoundedCornerShape(20.dp)) {
            // va visualizzata l'immagine dell'hero
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://example.com/image.jpg")
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.image_fill0_wght300_grad0_opsz48_light),
                error = painterResource(R.drawable.image_fill0_wght300_grad0_opsz48_light),
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
                modifier = Modifier.fillMaxSize().padding(all=MEDIUM_PADDING)
            ) {
                Text(
                    text = hero.name,
                    color = androidx.compose.material.MaterialTheme.colors.TopAppBarBackgroundColor
                )
            }
        }

    }
}