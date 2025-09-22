package com.gorcak.sportperformancetracker.record.presentation.new_record.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gorcak.sportperformancetracker.core.presentation.util.LeadingZeroVisualTransformation
import com.gorcak.sportperformancetracker.core.presentation.util.toFormatedTimeDigit
import org.jetbrains.compose.resources.stringResource
import sportperformancetracker.composeapp.generated.resources.Res
import sportperformancetracker.composeapp.generated.resources.label_hours
import sportperformancetracker.composeapp.generated.resources.label_minutes
import sportperformancetracker.composeapp.generated.resources.label_seconds

@Composable
fun TimerInput(
    hours: Int,
    minutes: Int,
    seconds: Int,
    onHoursChanged: (Int) -> Unit,
    onMinutesChanged: (Int) -> Unit,
    onSecondsChanged: (Int) -> Unit,
    isEnabled: Boolean,
    focusManager: FocusManager,
    initialFocusRequester: FocusRequester,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier = modifier ,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TimeInputBox(
            value = hours.toFormatedTimeDigit(),
            onValueChanged = {
                if (it.isEmpty()) {
                    onHoursChanged(0)
                }
                it.toIntOrNull()?.also { parsedHours ->
                    if (parsedHours < 100) {
                        onHoursChanged(parsedHours)
                    }
                }
            },
            modifier = Modifier.weight(1f)
                .focusRequester(initialFocusRequester) ,
            labelText = stringResource(Res.string.label_hours),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Right)
                }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
            isEnabled = isEnabled
        )
        TimeInputBox(
            value = minutes.toFormatedTimeDigit(),
            onValueChanged = {
                if (it.isEmpty()) {
                    onMinutesChanged(0)
                }
                it.toIntOrNull()?.also { parsedMinutes ->
                    if (parsedMinutes < 60) {
                        onMinutesChanged(parsedMinutes)
                    }
                }
            },
            modifier = Modifier.weight(1f) ,
            labelText = stringResource(Res.string.label_minutes),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Right)
                }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            ),
            isEnabled = isEnabled
        )

        TimeInputBox(
            value = seconds.toFormatedTimeDigit(),
            onValueChanged = {
                if (it.isEmpty()) {
                    onSecondsChanged(0)
                }
                it.toIntOrNull()?.also { parsedSeconds ->
                    if (parsedSeconds < 60) {
                        onSecondsChanged(parsedSeconds)
                    }
                }
            },
            modifier = Modifier.weight(1f),
            labelText = stringResource(Res.string.label_seconds),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            isEnabled = isEnabled
        )
    }
}

@Composable
private fun TimeInputBox(
    value: String,
    onValueChanged: (String) -> Unit,
    isEnabled: Boolean,
    modifier: Modifier = Modifier,
    labelText: String? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChanged,
        modifier = modifier
            .height(IntrinsicSize.Min) ,
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            fontWeight = FontWeight.W500
        ),
        enabled = isEnabled,
        singleLine = true,
        maxLines = 1,
        label = {
            labelText?.also { label ->
                Text(label)
            }
        },
        placeholder = {
            Text(
                "00",
                style = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                ),
                modifier = Modifier.fillMaxSize()
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        visualTransformation = LeadingZeroVisualTransformation(MaterialTheme.colorScheme.onBackground)
    )
}
