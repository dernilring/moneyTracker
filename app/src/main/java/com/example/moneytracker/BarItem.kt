package com.example.moneytracker

import android.media.Image
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import java.util.Vector

data class BarItem(
    val title: String,
    @DrawableRes val image: Int,
    val route: String
)
