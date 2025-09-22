package com.gorcak.sportperformancetracker.record.presentation.new_record.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun RecordInput(
    value: String,
    onTextChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    inputModifier: Modifier = Modifier,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isEnabled: Boolean = true,
    errorMessage: String? = null,
) {
    Column(
        modifier = modifier
            .fillMaxWidth() ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                onTextChange(it)
            },
            modifier = inputModifier.fillMaxWidth() ,
            label = {
                Text( text =  labelText)
            },
            isError = errorMessage != null,
            supportingText = {
                errorMessage?.let { message ->
                    Text(message, color = MaterialTheme.colorScheme.error)
                }
            },
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            enabled = isEnabled

        )

    }
}