@file:OptIn(ExperimentalMaterial3Api::class)

package com.gorcak.sportperformancetracker.record.presentation.new_record

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.displayCutoutPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.gorcak.sportperformancetracker.core.presentation.ObserveEventsEffect
import com.gorcak.sportperformancetracker.core.presentation.composables.TrackerScaffold
import com.gorcak.sportperformancetracker.core.presentation.composables.calculateWindowSizeClass
import com.gorcak.sportperformancetracker.record.presentation.new_record.composables.RecordInput
import com.gorcak.sportperformancetracker.record.presentation.new_record.composables.TimerInput
import com.gorcak.sportperformancetracker.record.presentation.new_record.composables.TitledRadioButton
import org.jetbrains.compose.resources.stringResource
import sportperformancetracker.composeapp.generated.resources.Res
import sportperformancetracker.composeapp.generated.resources.label_add_location
import sportperformancetracker.composeapp.generated.resources.label_add_name
import sportperformancetracker.composeapp.generated.resources.save_option
import sportperformancetracker.composeapp.generated.resources.save_record
import sportperformancetracker.composeapp.generated.resources.save_remote
import sportperformancetracker.composeapp.generated.resources.title_create_new_record

@Composable
fun NewRecordScreenRoot(
    viewModel: NewRecordViewModel,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveEventsEffect(flow = viewModel.events) { event ->
        when (event) {
            NewRecordEvent.RecordCreateFailure -> {}
            NewRecordEvent.RecordCreateSuccess -> {
                onBackClick()
            }
        }
    }

    NewRecordScreen(
        state = state,
        onAction = { action ->
            when (action) {
                NewRecordAction.OnBackClick -> onBackClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
fun NewRecordScreen(
    state: NewRecordState,
    onAction: (NewRecordAction) -> Unit
) {
    val windowSizeClass = calculateWindowSizeClass()
    val addHorizontalPadding = windowSizeClass.widthSizeClass != WindowWidthSizeClass.Compact

    val focusRequester = remember { FocusRequester() }
    val timerFocusRequester = remember { FocusRequester() }

    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    TrackerScaffold(
        modifier = Modifier
            .fillMaxSize()
            .then(if (addHorizontalPadding) Modifier.navigationBarsPadding() else Modifier),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(Res.string.title_create_new_record),
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onAction(NewRecordAction.OnBackClick)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onTertiary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
            )
        }
    ) { insets ->
        Column(
            modifier = Modifier
                .padding(insets)
                .padding(16.dp)
                .then(if (addHorizontalPadding) Modifier.displayCutoutPadding() else Modifier)
        ) {
            val inputs: @Composable () -> Unit = {
                RecordInput(
                    value = state.name,
                    onTextChange = {
                        onAction(NewRecordAction.OnNameChanged(it))
                    },
                    labelText = stringResource(Res.string.label_add_name),
                    inputModifier = Modifier
                        .focusRequester(focusRequester),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    isEnabled = !state.isSaving
                )
                RecordInput(
                    value = state.location,
                    onTextChange = {
                        onAction(NewRecordAction.OnLocationChanged(it))
                    },
                    labelText = stringResource(Res.string.label_add_location),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            timerFocusRequester.requestFocus()
                        }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    isEnabled = !state.isSaving,
                    inputModifier = Modifier
                        .focusProperties {
                            next = timerFocusRequester
                        }
                )
                TimerInput(
                    hours = state.duration.hours,
                    minutes = state.duration.minutes,
                    seconds = state.duration.seconds,
                    onHoursChanged = { hours ->
                        onAction(NewRecordAction.OnDurationHoursChanged(hours))
                    },
                    onMinutesChanged = { minutes ->
                        onAction(NewRecordAction.OnDurationMinutesChanged(minutes))
                    },
                    onSecondsChanged = { seconds ->
                        onAction(NewRecordAction.OnDurationSecondsChanged(seconds))
                    },
                    isEnabled = !state.isSaving,
                    focusManager = focusManager,
                    initialFocusRequester = timerFocusRequester
                )
                Spacer(modifier = Modifier.height(32.dp))

            }


            val controls: @Composable () -> Unit = {
                Spacer(modifier = Modifier.height(32.dp))
                TitledRadioButton(
                    isSelected = !state.useRemoteStorage,
                    title = stringResource(Res.string.save_option),
                    onSelected = {
                        onAction(NewRecordAction.OnStorageTypeChanged(useRemoteStorage = false))
                    }
                )
                TitledRadioButton(
                    isSelected = state.useRemoteStorage,
                    title = stringResource(Res.string.save_remote),
                    onSelected = {
                        onAction(NewRecordAction.OnStorageTypeChanged(useRemoteStorage = true))
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        onAction(NewRecordAction.OnSaveClick)
                    },
                    enabled = state.canSaveRecord,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(Res.string.save_record),
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                    inputs()
                    HorizontalDivider(
                        modifier = Modifier.height(1.dp)
                            .background(MaterialTheme.colorScheme.onBackground)
                    )
                    controls()
                }

                else -> {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.spacedBy(32.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                        ) {
                            inputs()
                        }
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .weight(1f)
                        ) {
                            controls()
                        }
                    }
                }
            }


        }
    }
}
