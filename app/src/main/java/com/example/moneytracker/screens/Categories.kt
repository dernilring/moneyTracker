package com.example.moneytracker.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.moneytracker.Category

@Composable
fun Categories() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerpadding ->
        Column( modifier = Modifier.padding(innerpadding).padding(horizontal = 16.dp, vertical = 32.dp)){
        Text(
            text = "categories",

        )
            Category.entries.forEach { category ->
                Row(
                    modifier = Modifier.padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ){
                    Text(text= category.displayedName)
                    Icon(
                        imageVector = ImageVector.vectorResource(category.icon),
                        contentDescription = category.displayedName
                    )
                }

            }
        }

    }
}
