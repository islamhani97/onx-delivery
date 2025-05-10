package com.islam97.android.apps.onx.presentation.orders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.islam97.android.apps.onx.presentation.ui.theme.appColorScheme

@Composable
fun OrderItemField(modifier: Modifier = Modifier, label: String, value: String, valueColor: Color) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.appColorScheme.neutralDark, textAlign = TextAlign.Center
            )
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            text = value,
            style = MaterialTheme.typography.titleMedium.copy(
                color = valueColor, textAlign = TextAlign.Center
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderItemField() {
    OrderItemField(
        label = "Label", value = "Value", valueColor = MaterialTheme.appColorScheme.primary
    )
}