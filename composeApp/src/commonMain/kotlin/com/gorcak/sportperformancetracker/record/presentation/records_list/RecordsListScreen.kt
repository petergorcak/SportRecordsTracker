@file:OptIn(ExperimentalMaterial3Api::class)

package com.gorcak.sportperformancetracker.record.presentation.records_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.CloudDone
import androidx.compose.material.icons.outlined.SdStorage
import androidx.compose.material.icons.outlined.SelectAll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gorcak.sportperformancetracker.core.presentation.composables.TrackerScaffold
import com.gorcak.sportperformancetracker.core.presentation.composables.calculateWindowSizeClass
import com.gorcak.sportperformancetracker.record.presentation.records_list.composables.RecordsList
import com.gorcak.sportperformancetracker.record.presentation.records_list.composables.TitledRailItem
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import sportperformancetracker.composeapp.generated.resources.Res
import sportperformancetracker.composeapp.generated.resources.add_new_record
import sportperformancetracker.composeapp.generated.resources.tab_all
import sportperformancetracker.composeapp.generated.resources.tab_local
import sportperformancetracker.composeapp.generated.resources.tab_remote
import sportperformancetracker.composeapp.generated.resources.title_records_list

@Composable
fun RecordsListScreenRoot(
    viewModel: RecordsListViewModel = koinViewModel(),
    onAddNewRecord: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    RecordsListScreen(
        state = state,
        onAction = { action ->
            when (action) {
                RecordsListAction.AddNewRecord -> onAddNewRecord()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable()
fun RecordsListScreen(
    state: RecordsListState,
    onAction: (RecordsListAction) -> Unit
) {
    val windowSizeClass = calculateWindowSizeClass()
    val addHorizontalPadding = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact
    val pagerState = rememberPagerState { 3 }
    val sectionItems = listOf(
        Triple(0, Res.string.tab_all, Icons.Outlined.SelectAll),
        Triple(1, Res.string.tab_local, Icons.Outlined.SdStorage),
        Triple(2, Res.string.tab_remote, Icons.Outlined.CloudDone),
    )

    LaunchedEffect(state.selectedTab) {
        pagerState.animateScrollToPage(state.selectedTab)
    }
    LaunchedEffect(pagerState.targetPage) {
        if (state.selectedTab != pagerState.targetPage) {
            onAction(RecordsListAction.OnTabSelected(pagerState.targetPage))
        }
    }
    TrackerScaffold(
        modifier = Modifier
            .fillMaxSize()
            .then(if (addHorizontalPadding) Modifier.navigationBarsPadding() else Modifier),
        fab = {
            FloatingActionButton(
                onClick = {
                    onAction(RecordsListAction.AddNewRecord)
                },
            ) {
                Icon(
                    imageVector = Icons.Outlined.Add,
                    contentDescription = stringResource(Res.string.add_new_record)
                )
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(Res.string.title_records_list),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
            )
        }
    ) { insets ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(insets)
                .then(if (addHorizontalPadding) Modifier.displayCutoutPadding() else Modifier)
        ) {
            val horizontalPager : @Composable () -> Unit = {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth()
                        .weight(1f)
                ) { pageIndex ->
                    RecordsList(
                        records = when (pageIndex) {
                            0 -> state.allRecords
                            1 -> state.localRecords
                            2 -> state.remoteRecords
                            else -> emptyList()
                        },
                        onDeleteClicked = {
                            onAction(RecordsListAction.DeleteRecord(it))
                        }
                    )
                }
            }
            when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                    TabRow(
                        selectedTabIndex = state.selectedTab,
                        modifier = Modifier.fillMaxWidth()
                            .fillMaxWidth()
                            .background(Color.Red),
                    ) {
                        sectionItems.forEach { (index, labelRes, _) ->
                            Tab(
                                selected = state.selectedTab == index,
                                modifier = Modifier.weight(1f),
                                onClick = {
                                    onAction(RecordsListAction.OnTabSelected(index))
                                }
                            ) {
                                Text(
                                    text = stringResource(labelRes),
                                    modifier = Modifier.padding(vertical = 12.dp),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    horizontalPager()
                }

                else -> {
                    Row(modifier = Modifier.fillMaxSize()) {
                        NavigationRail(
                            modifier = Modifier.fillMaxHeight()
                        ) {
                            sectionItems.forEach { (index, labelRes, icon) ->
                                TitledRailItem(
                                    isSelected = state.selectedTab == index,
                                    onClick = {
                                        onAction(RecordsListAction.OnTabSelected(index))
                                    },
                                    iconVector = icon,
                                    titleRes = labelRes
                                )
                            }

                        }
                        horizontalPager()
                    }
                }
            }

        }
    }

}


