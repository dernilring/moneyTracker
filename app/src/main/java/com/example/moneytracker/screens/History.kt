package com.example.moneytracker.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.moneytracker.Category
import com.example.moneytracker.MoneyViewModel
import com.example.moneytracker.NavRoutes
import com.example.moneytracker.Sorting
import com.example.moneytracker.Spending
import com.example.moneytracker.Spendings
import com.example.moneytracker.sortedBySum

@Composable
fun SpendingHistory(
    viewModel: MoneyViewModel,
    selectedId: String?,
    onLongItemClick: (Spending) -> Unit,
    onAddClick: () ->Unit,
) {
    val context = LocalContext.current
    var categoryMenuExpanded by remember { mutableStateOf(false) }
    var sortMenuExpanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    var sorting by remember { mutableStateOf<Sorting?>(null) }


    val visibleSpendings = viewModel.spendings
        .filter { selectedCategory == null || it.category == selectedCategory }
        .let { list -> sorting?.let { list.sortedBySum(it) } ?: list }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                onClick = onAddClick
            ) {
                Text(text = "+", fontSize = 24.sp)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box {
                    FilterChip(
                        selected = selectedCategory != null,
                        onClick = { categoryMenuExpanded = true },
                        label = {
                            Text(selectedCategory?.displayedName ?: "Все категории")
                        }
                    )
                    DropdownMenu(
                        expanded = categoryMenuExpanded,
                        onDismissRequest = { categoryMenuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Все категории") },
                            onClick = {
                                selectedCategory = null
                                categoryMenuExpanded = false
                            }
                        )
                        Category.entries.forEach { category ->
                            DropdownMenuItem(
                                text = { Text(category.displayedName) },
                                onClick = {
                                    selectedCategory = category
                                    categoryMenuExpanded = false
                                }
                            )
                        }
                    }
                }

                Box {
                    FilterChip(
                        selected = sorting != null,
                        onClick = { sortMenuExpanded = true },
                        label = {
                            Text(sorting?.displayName ?: "Без сортировки")
                        }
                    )
                    DropdownMenu(
                        expanded = sortMenuExpanded,
                        onDismissRequest = { sortMenuExpanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Без сортировки") },
                            onClick = {
                                sorting = null
                                sortMenuExpanded = false
                            }
                        )
                        Sorting.entries.forEach { option ->
                            DropdownMenuItem(
                                text = { Text(option.displayName) },
                                onClick = {
                                    sorting = option
                                    sortMenuExpanded = false
                                }
                            )
                        }
                    }
                }
            }

            Spendings(
                spendings = visibleSpendings,
                selectedId = selectedId,
                modifier = Modifier.weight(1f),
                onItemClick = { currSp ->
                    Toast.makeText(
                        context,
                        "${currSp.title} — ${currSp.amount}",
                        Toast.LENGTH_LONG
                    ).show()
                },
                onLongItemClick = onLongItemClick
            )
        }
    }
}

private fun List<Spending>.sortedBySum(order: Sorting): List<Spending> = when (order) {
    Sorting.ASCENDING -> sortedBy { it.amount }
    Sorting.DECREASING -> sortedByDescending { it.amount }
}
