package com.islam97.android.apps.onx.presentation.ui.composeables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.islam97.android.apps.onx.presentation.ui.theme.appColorScheme
import com.islam97.android.apps.onx.presentation.ui.theme.appShapes

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    placeholder: String,
) {
    OutlinedTextField(
        modifier = modifier
            .clip(MaterialTheme.appShapes.textFieldShape)
            .background(MaterialTheme.appColorScheme.tertiaryExtremeLight),
        value = value,
        onValueChange = onValueChange,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        colors = OutlinedTextFieldDefaults.colors().copy(
            focusedContainerColor = MaterialTheme.appColorScheme.transparent,
            unfocusedContainerColor = MaterialTheme.appColorScheme.transparent,
            focusedIndicatorColor = MaterialTheme.appColorScheme.transparent,
            unfocusedIndicatorColor = MaterialTheme.appColorScheme.transparent
        ),
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        singleLine = true,
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(), text = placeholder, style = TextStyle(
                    color = MaterialTheme.appColorScheme.black, textAlign = TextAlign.Center
                )
            )
        })
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomTextField() {
    CustomTextField(value = "", onValueChange = {}, placeholder = "Placeholder")
}