package com.islam97.android.apps.onx.presentation.ui.composeables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.islam97.android.apps.onx.presentation.ui.theme.appColorScheme

@Composable
fun CustomSingleChoiceSegmentedRow(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOptionIndex: Int,
    onOptionSelected: (index: Int) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(50),
        colors = CardDefaults.cardColors()
            .copy(containerColor = MaterialTheme.appColorScheme.white),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row {
            options.forEachIndexed { index, text ->
                val isSelected = index == selectedOptionIndex
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (isSelected) MaterialTheme.appColorScheme.primary else MaterialTheme.appColorScheme.white)
                        .clickable {
                            if (index != selectedOptionIndex) {
                                onOptionSelected(index)
                            }
                        }) {
                    Text(
                        modifier = Modifier.padding(
                            horizontal = 16.dp, vertical = 12.dp
                        ),
                        text = text,
                        style = TextStyle(color = if (isSelected) MaterialTheme.appColorScheme.white else MaterialTheme.appColorScheme.primary)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomSingleChoiceSegmentedRow() {
    CustomSingleChoiceSegmentedRow(
        options = listOf("Option 1", "Option 2", "Option 3"), selectedOptionIndex = 0
    ) {}
}