package com.islam97.android.apps.onx.presentation.ui.composeables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.islam97.android.apps.onx.presentation.ui.theme.appColorScheme
import com.islam97.android.apps.onx.presentation.ui.theme.appShapes

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        shape = MaterialTheme.appShapes.buttonShape,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.appColorScheme.primary),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomButton() {
    CustomButton(modifier = Modifier, onClick = {}) {}
}