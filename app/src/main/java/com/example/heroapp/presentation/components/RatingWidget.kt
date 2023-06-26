package com.example.heroapp.presentation.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.stringResource
import com.example.heroapp.R
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.heroapp.ui.theme.SMALL_PADDING
import com.example.heroapp.ui.theme.StarColor


@SuppressLint("RememberReturnType")
@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Double,
    scaleFactor: Float = 2f,
    spaceBetween: Dp = SMALL_PADDING
){
    // Path String
    val starStringPath = stringResource(id = R.string.star_path_full)
    // Path Object, il risultato deve essere un path Object
    val star_path = remember {
        PathParser().parsePathString(pathData = starStringPath).toPath()
    }
    // get bounds of the image:
    val path_bounds = remember {
        star_path.getBounds()
    }
    val result = calculateRating(rating = rating)
    Column {
  //      Spacer(Modifier.padding(30.dp))
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(spaceBetween)
        ) {
            val filledStar = result["filledStart"]
            filledStar?.let {
                repeat(it) {
                    FullFilledStar(
                        path = star_path,
                        starPathBounds = path_bounds,
                        scaleFactor = scaleFactor

                    )
                }
            }
            val halfFilledStar = result["halfFilledStart"]
            halfFilledStar?.let {
                repeat(it) {
                    HalfFilledStar(
                        path = star_path,
                        starPathBounds = path_bounds,
                        scaleFactor = scaleFactor

                    )
                }
            }
            val emptyStar = result["emptyStar"]
            emptyStar?.let {
                // Path String Empty
                val starStringPath_empty = stringResource(id = R.string.star_path_empty)
                // Path Object Empty
                val star_path_empty = remember {
                    PathParser().parsePathString(pathData = starStringPath_empty).toPath()
                }
                // get bounds of the image:
                val path_bounds_empty = remember {
                    star_path_empty.getBounds()
                }
                repeat(it) {
                    EmptyStar(
                        path = star_path_empty,
                        starPathBounds = path_bounds_empty,
                        scaleFactor = scaleFactor

                    )
                }
            }
        }
    }

}

@Composable
fun calculateRating(rating: Double): Map<String, Int> {
    val maxRating by remember {
        mutableStateOf(5)
    }
    var halfRatingStar by remember {
        mutableStateOf(0)
    }
    var emptyRatingStar by remember {
        mutableStateOf(0)
    }
    var fullRatingStar by remember {
        mutableStateOf(0)
    }
    // when rating changes, recalculation takes place
    LaunchedEffect(
        key1 = rating
    ){
        val(fullStar, halfStar) = rating
                                .toString()
                                .split(".")
                                .map { it.toInt() }

        // fullStar must be between 0 and 5, halfStar between 0 and 9
        if(fullStar in 0..5 && halfStar in (0..9))
        {
            fullRatingStar = fullStar
            if (halfStar in 1..5)
                halfRatingStar++
            if (fullStar == 5 && halfStar > 0)
            {
                emptyRatingStar = 5
                halfRatingStar = 0
                fullRatingStar = 0
            }
            else if(halfStar in 6..9)
                fullRatingStar++
        }
        else
            Log.d("Rating Widget", "Wrong Rating Value")
    }
    emptyRatingStar = maxRating - (fullRatingStar + halfRatingStar)
    return mapOf(
        "filledStart" to fullRatingStar,
        "halfFilledStart" to halfRatingStar,
        "emptyStar" to emptyRatingStar
    )
}

@Composable
fun FullFilledStar(
    path: Path,
    starPathBounds: Rect,
    scaleFactor: Float
){
    Canvas(
        modifier = Modifier.size(24.dp),
    ) {
        val canvasSize = this.size
        val path_width = starPathBounds.width
        val path_height = starPathBounds.height
        val centerLeft = canvasSize.width/2
        val centerTop = canvasSize.height/2
        val newCenter_left = centerLeft - (path_width/1.7f) // si va un po'a tentativi
        val newCenter_Top = centerTop - (path_height/1.5f) // si va un po'a tentativi

        scale(
            scale = scaleFactor
        ){
            // la funzione translate ci aiuta con il posizionamento della stella
            this.translate(
                left = newCenter_left,
                top = newCenter_Top
            ){
                drawPath(
                    path = path,
                    color = StarColor
                )
            }
        }

    }
}

@Composable
fun EmptyStar(
    path: Path,
    starPathBounds: Rect,
    scaleFactor: Float
){
    Canvas(
        modifier = Modifier.size(24.dp),
    ) {
        val canvasSize = this.size
        val path_width = starPathBounds.width
        val path_height = starPathBounds.height
        val centerLeft = canvasSize.width/2
        val centerTop = canvasSize.height/2
        val newCenter_left = centerLeft - (path_width/1.7f) // si va un po'a tentativi
        val newCenter_Top = centerTop - (path_height/1.5f) // si va un po'a tentativi

        scale(
            scale = scaleFactor
        ){
            // la funzione translate ci aiuta con il posizionamento della stella
            this.translate(
                left = newCenter_left,
                top = newCenter_Top
            ){
                drawPath(
                    path = path,
                    color = Color.LightGray
                )
            }
        }

    }
}


@Composable
fun HalfFilledStar(
    path: Path,
    starPathBounds: Rect,
    scaleFactor: Float
){
    Canvas(
        modifier = Modifier.size(24.dp),
    ) {
        val canvasSize = this.size
        val path_width = starPathBounds.width
        val path_height = starPathBounds.height
        val centerLeft = canvasSize.width/2
        val centerTop = canvasSize.height/2
        val newCenter_left = centerLeft - (path_width/1.7f) // si va un po'a tentativi
        val newCenter_Top = centerTop - (path_height/1.5f) // si va un po'a tentativi

        scale(
            scale = scaleFactor
        ){
            // la funzione translate ci aiuta con il posizionamento della stella
            this.translate(
                left = newCenter_left,
                top = newCenter_Top
            ){
                drawPath(
                    path = path,
                    color = Color.Gray
                )
                // per colorare metà stella, ci sovrapponiamo (fino a metà) un rettangolo del colore di cui vogliamo colorare
                clipPath(
                    path = path
                ) {
                    drawRect(
                        color = StarColor,
                        size = Size(
                            width = starPathBounds.maxDimension/1.7f ,
                            height = starPathBounds.maxDimension * scaleFactor
                        )
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFullRating() {
    // Path String
    val starStringPath = stringResource(id = R.string.star_path_full)
    // Path Object, il risultato deve essere un path Object
    val star_path = remember {
        PathParser().parsePathString(pathData = starStringPath).toPath()
    }
    // get bounds of the image:
    val path_bounds = remember {
        star_path.getBounds()
    }
    FullFilledStar(
        path = star_path,
        starPathBounds = path_bounds,
        scaleFactor = 1.5f
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewHalfRating() {

    // Path String
    val starStringPath = stringResource(id = R.string.star_path_full)
    // Path Object, il risultato deve essere un path Object
    val star_path = remember {
        PathParser().parsePathString(pathData = starStringPath).toPath()
    }
    // get bounds of the image:
    val path_bounds = remember {
        star_path.getBounds()
    }
    HalfFilledStar(
        path = star_path,
        starPathBounds = path_bounds,
        scaleFactor = 1.5f
    )
}

@Preview(showBackground = true)
@Composable
fun EmptyRating() {

    // Path String
    val starStringPath = stringResource(id = R.string.star_path_empty)
    // Path Object, il risultato deve essere un path Object
    val star_path = remember {
        PathParser().parsePathString(pathData = starStringPath).toPath()
    }
    // get bounds of the image:
    val path_bounds = remember {
        star_path.getBounds()
    }
    EmptyStar(
        path = star_path,
        starPathBounds = path_bounds,
        scaleFactor = 1.5f
    )
}






