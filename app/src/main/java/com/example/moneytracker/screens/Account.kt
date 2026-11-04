package com.example.moneytracker.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Account(){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerpadding ->
        Column( modifier = Modifier.padding(horizontal = 16.dp, vertical = 32.dp)){
            Text(text = "username: dernilring",
                modifier = Modifier
                    .padding(innerpadding)

            )
            Text(text= "email: dolgaia.d@mail.ru")
        }

    }
}
