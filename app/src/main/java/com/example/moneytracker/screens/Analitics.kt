package com.example.moneytracker.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Analitics(){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerpadding ->
        Text(text = "analitics",
            modifier = Modifier.padding(innerpadding))
    }
}
