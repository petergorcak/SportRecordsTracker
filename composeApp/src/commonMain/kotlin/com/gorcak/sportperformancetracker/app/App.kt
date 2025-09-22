@file:OptIn(ExperimentalMaterial3Api::class)

package com.gorcak.sportperformancetracker.app

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.gorcak.sportperformancetracker.core.presentation.designsystem.SportPerformanceTrackerTheme
import com.gorcak.sportperformancetracker.record.presentation.new_record.NewRecordScreenRoot
import com.gorcak.sportperformancetracker.record.presentation.new_record.NewRecordViewModel
import com.gorcak.sportperformancetracker.record.presentation.records_list.RecordsListScreenRoot
import com.gorcak.sportperformancetracker.record.presentation.records_list.RecordsListViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App() {
    SportPerformanceTrackerTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = NavRoute.RootGraph
        ) {
            navigation<NavRoute.RootGraph>(
                startDestination = NavRoute.RecordsList
            ) {
                composable<NavRoute.RecordsList> {
                    val viewModel = koinViewModel<RecordsListViewModel>()
                    RecordsListScreenRoot(
                        viewModel = viewModel,
                        onAddNewRecord = {
                            navController.navigate(NavRoute.CreateRecord)
                        }
                    )
                }

                composable<NavRoute.CreateRecord> {
                    val viewModel = koinViewModel<NewRecordViewModel>()
                        NewRecordScreenRoot(
                            viewModel = viewModel,
                            onBackClick = {
                                navController.navigateUp()
                            }
                        )
                }
            }
        }
    }
}