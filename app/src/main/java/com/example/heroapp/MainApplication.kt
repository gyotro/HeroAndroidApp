package com.example.heroapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// va creata l'Application per la dependency injection
@HiltAndroidApp
class MainApplication: Application()