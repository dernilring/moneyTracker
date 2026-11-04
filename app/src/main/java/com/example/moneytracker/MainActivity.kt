package com.example.moneytracker

import android.R.attr.contentDescription
import android.R.attr.text
import android.os.Bundle
import android.widget.Toast
import androidx.compose.runtime.setValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.currentCompositionErrors
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoneyTrackerTheme {
                Main()
            }
        }
    }


}
@Composable
fun Main() {
val navController = rememberNavController()
    Column(
        Modifier.padding(10.dp)
    ) {
        NavHost(navController, startDestination = NavRoutes.Home.route, modifier = Modifier.weight(1f)){
            composable(NavRoutes.Home.route) {Home()}
            composable(NavRoutes.Account.route) {Account()}
            composable(NavRoutes.Analitics.route) {Analitics()}
            composable(NavRoutes.Categories.route) {Categories()}
            composable(NavRoutes.SpendingHistory.route) {SpendingHistory()}
        }
        BottomNavBar(navController = navController)
    }
}
@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar{
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { navItem->
            NavigationBarItem(
                selected = currentRoute == navItem.route,
                onClick = {
                        navController.navigate(navItem.route){
                            popUpTo(navController.graph.findStartDestination().id) {saveState = true}
                             launchSingleTop = true
                              restoreState = true
                        }
                },
                icon ={
                    Icon(
                        imageVector = ImageVector.vectorResource(navItem.image),
                        contentDescription = navItem.title
                    )
                },
                label ={
                    Text(text = navItem.title)
                }
            )
        }
    }
}
@Composable
fun Home(){
    Text(text = "home")
}
@Composable
fun Account(){
    Text(text = "acc")
}
@Composable
fun Analitics(){
    Text(text = "analitics")
}
@Composable
fun Categories(){
Text(text = "categories")
}
@Composable
fun SpendingHistory(){
    val context = LocalContext.current
    var selectedId by remember { mutableStateOf<String?>(null) }
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
                selectedId = selectedId,
                modifier = Modifier.padding(innerPadding),
                onItemClick = { currSp ->
                    Toast.makeText(
                        context,
                        "${currSp.title} — ${currSp.amount}",
                        Toast.LENGTH_LONG
                    ).show()

                },
                onLongItemClick = { spending ->
                    selectedId = if (selectedId == spending.id) null else spending.id
                }
            )
        }
    }



@Composable
fun Spendings(spendings: List<Spending>,
              selectedId: String?,
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
            val showInfo = selectedId == currSpending.id
            val blurModifier = if (selectedId!= null && !showInfo){
                Modifier.blur(10.dp)

            } else {
                Modifier
            }
            SpendingCard(
                spending = currSpending,
                showInfo = showInfo,
                modifier = blurModifier,
                onItemClick =onItemClick,
                onLongItemClick = onLongItemClick
            )
        }
    }
}

@Composable
fun SpendingCard(
        spending: Spending,
        showInfo: Boolean = false,
        modifier: Modifier,
        onItemClick: (Spending) -> Unit,
        onLongItemClick: (Spending) -> Unit
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .heightIn( min = if (showInfo) 140.dp else 72.dp)
            .combinedClickable(
                onClick ={ onItemClick(spending)},
                onLongClick = { onLongItemClick(spending)}
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = if (showInfo)
                 10.dp else 4.dp)

    ){
        Row(
            modifier = Modifier
             .fillMaxWidth()
             .background(Color.LightGray)
             .padding( if (showInfo) 24.dp else 16.dp),
            verticalAlignment = Alignment.Top
        ){
            Column(modifier = Modifier.weight(1f)){
                Text(
                    text = spending.title,
                    fontSize = if (showInfo) 24.sp else 18.sp
                )
                Text(
                    text = "${spending.amount} $",
                    fontSize = 18.sp,
                    color = Color.Red
                )
                if (showInfo){
                     spending.note?.let{
                         Text(it, fontSize = 15.sp)
                     }
                    Text(text = spending.date, fontSize = 15.sp)
                    Text(text = spending.category, fontSize = 15.sp)
                }
            }

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
            selectedId = null,
            onItemClick = {},
            onLongItemClick = {}
        )
    }
}