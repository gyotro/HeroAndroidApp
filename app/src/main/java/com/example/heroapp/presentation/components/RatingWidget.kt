package com.example.heroapp.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.res.stringResource
import com.example.heroapp.R
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.heroapp.ui.theme.StarColor

@SuppressLint("RememberReturnType")
@Composable
fun RatingWidget(
    modifier: Modifier,
    rating: Float
){
    // Path String
    val starStringPath = stringResource(id = R.string.star_path)
    // Path Object, il risultato deve essere un path Object
    val star_path = remember {
        PathParser().parsePathString(pathData = starStringPath).toPath()
    }
    // get bounds of the image:
    val path_bounds = remember {
        star_path.getBounds()
    }
    FilledStar(
        path = star_path,
        starPathBounds = path_bounds,
        scaleFactor = 1.5f
    )
}

@Composable
fun FilledStar(
    path: Path,
    starPathBounds: Rect,
    scaleFactor: Float = 1f
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

@Preview(showBackground = true)
@Composable
fun PreviewRating() {
    RatingWidget(
        modifier = Modifier,
        rating = 1f,
    )
}




