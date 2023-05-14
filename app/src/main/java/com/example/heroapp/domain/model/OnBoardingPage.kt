package com.example.heroapp.domain.model

import androidx.annotation.DrawableRes
import com.example.heroapp.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.greetings,
        title = "Greetings",
        description = "Description for Greetings Image"
    )

    object Second : OnBoardingPage(
        image = R.drawable.explore,
        title = "Explore",
        description = "Description for explore Image"
    )

    object Third : OnBoardingPage(
        image = R.drawable.power,
        title = "Power",
        description = "Description for power Image"
    )
}