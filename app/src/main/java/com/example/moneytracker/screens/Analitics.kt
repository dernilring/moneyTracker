package com.example.moneytracker.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moneytracker.Category
import com.example.moneytracker.CategoryColor
import com.example.moneytracker.MoneyViewModel
import com.example.moneytracker.PieChartCanvas
import com.example.moneytracker.buildPieSectors
import com.example.moneytracker.ui.theme.Food

@Composable
fun Analitics(
    viewModel: MoneyViewModel
){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerpadding ->
        Column(modifier = Modifier.padding(innerpadding)) {
            Text(text = "analitics")
            Text(text = "всего потрачено")
            Text(text = "%.2f".format(viewModel.totalSum))

            val sectors = remember(viewModel.spendings){
                buildPieSectors(viewModel.spendings)
            }
            PieChartCanvas(sectors)
            DrawRow(Category.FOOD, "Food", viewModel)
            DrawRow(Category.HEALTH, "Health", viewModel)
            DrawRow(Category.ENTERTAINMENT, "Entertainment", viewModel)
            DrawRow(Category.TRANSPORT, "Transport", viewModel)
            DrawRow(Category.HOME, "Home", viewModel)

        }

    }
}

@Composable
fun DrawRow(category: Category, text : String, viewModel: MoneyViewModel){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(CategoryColor[category] ?: Color.LightGray)
        )
        Text(text = " $text : ${viewModel.sumOfCategory(category)}")

    }
}
