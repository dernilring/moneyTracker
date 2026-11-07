package com.example.moneytracker.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.moneytracker.MoneyViewModel

@Composable
fun Analitics(
    viewModel: MoneyViewModel
){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerpadding ->
        Column(modifier = Modifier.padding(innerpadding)) {
            Text(text = "analitics")
            Text(text = "всего потрачено")
            Text(text = "%.2f".format(viewModel.totalSum))

        }

    }
}
