package com.islam97.android.apps.onx.presentation.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class AppShapes(
    val textFieldShape: Shape = RoundedCornerShape(50),
    val buttonShape: Shape = RoundedCornerShape(50),
    val languageOptionShape: Shape = RoundedCornerShape(8.dp)
)