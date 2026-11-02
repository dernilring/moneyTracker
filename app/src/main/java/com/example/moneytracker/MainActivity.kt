package com.example.moneytracker

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneytracker.ui.theme.MoneyTrackerTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.currentCompositionErrors
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoneyTrackerTheme {
                val context = LocalContext.current
                val viewModel: MoneyViewModel = viewModel()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    floatingActionButton = {
                        FloatingActionButton(
                            shape = CircleShape,
                            onClick = { viewModel.addToList() }
                        ){
                            Text(text= "+", fontSize = 24.sp)
                        }
                    }
                ) { innerPadding ->
                        Spendings(
                            spendings = viewModel.spendings,
                            modifier = Modifier.padding(innerPadding),
                            onItemClick = { currSp ->
                                Toast.makeText(
                                    context,
                                    "${currSp.title} — ${currSp.amount}",
                                    Toast.LENGTH_LONG
                                ).show()

                            },
                            onLongItemClick = { spendingToRemove ->
                                viewModel.removeFromList(spendingToRemove)
                            }
                        )
                }
            }
        }
    }


}

@Composable
fun Spendings(spendings: List<Spending>,
              modifier: Modifier = Modifier,
              onItemClick: (Spending) -> Unit,
              onLongItemClick: (Spending) -> Unit,
              ) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        items(spendings, key = {it.id}){ currSpending ->
            SpendingCard(
                spending = currSpending,
                onItemClick =onItemClick,
                onLongItemClick = onLongItemClick
            )



        }
    }
}

@Composable
fun SpendingCard(
        spending: Spending,
        onItemClick: (Spending) -> Unit,
        onLongItemClick: (Spending) -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .combinedClickable(
                onClick ={ onItemClick(spending)},
                onLongClick = { onLongItemClick(spending)}
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

    ){
        Row(
            modifier = Modifier
             .fillMaxWidth()
             .background(Color.LightGray)
             .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = spending.title
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
               text = "${spending.amount} $"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    MoneyTrackerTheme {
        val sample = listOf<Spending>(
            Spending(category = "food", title = "Сыр", amount = 4.08, date = "02-11-2026", note = null),
            Spending(category = "food", title = "Хлеб", amount = 1.25, date = "02-11-2026", note = "бородинский"),
            Spending(category = "food", title = "Сыр", amount = 4.08, date = "01-11-2026", note = null),
            Spending(category = "food", title = "Яблоки", amount = 2.70, date = "01-11-2026", note = "1 кг"),
            Spending(category = "food", title = "Сыр", amount = 4.08, date = "30-10-2026", note = null),
            Spending(category = "food", title = "Батон", amount = 0.95, date = "30-10-2026", note = null)
        )
        Spendings(
            spendings = sample,
            onItemClick = {},
            onLongItemClick = {}
        )
    }
}