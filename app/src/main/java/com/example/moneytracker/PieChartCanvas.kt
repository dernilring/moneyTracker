package com.example.moneytracker

import android.R
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.key.Key.Companion.Six
import androidx.compose.ui.unit.dp


@Composable
fun PieChartCanvas(sectors: List<Sector>){
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .padding(16.dp)

    ){

        if (sectors.isEmpty()) return@Canvas

        val diameter = minOf(size.width, size.height)
        val radius = diameter / 2f
        val topLeft = Offset(
            x = ( size.width - diameter) / 2f,
            y = (size.height - diameter) / 2f
        )


        val arcSize = Size(diameter, diameter)

        sectors.forEach { sector ->
            drawArc(
                color = CategoryColor[sector.category] ?: Color.Black,
                startAngle = sector.startAngle,
                sweepAngle = sector.sizeAngle,
                useCenter = true,
                topLeft = topLeft,
                size = arcSize
            )
        }
        sectors.forEach { sector ->
            drawArc(
                color = Color.White,
                startAngle = sector.startAngle,
                sweepAngle = sector.sizeAngle,
                useCenter = true,
                topLeft = topLeft,
                size = arcSize,
                style = Stroke(width = 2.dp.toPx())
            )
        }
    }
}