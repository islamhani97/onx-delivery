package com.islam97.android.apps.onx.presentation.language

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.islam97.android.apps.onx.presentation.ui.theme.appColorScheme
import com.islam97.android.apps.onx.presentation.ui.theme.appShapes

@Composable
fun LanguageOption(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    language: Language,
    isSelected: Boolean,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(
            width = 1.dp, color = if (isSelected) {
                MaterialTheme.appColorScheme.secondaryDark
            } else {
                MaterialTheme.appColorScheme.tertiary
            }
        ),
        shape = MaterialTheme.appShapes.languageOptionShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSelected) {
                MaterialTheme.appColorScheme.secondaryLight
            } else {
                MaterialTheme.appColorScheme.white
            }
        ),
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp)
                .padding(end = 4.dp),
            painter = painterResource(id = language.iconResourceId),
            contentDescription = "",
            tint = Color.Unspecified
        )

        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = stringResource(language.textResourceId),
            style = TextStyle(color = MaterialTheme.appColorScheme.black)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLanguageOption() {
    LanguageOption(
        modifier = Modifier, onClick = {}, language = Language.ARABIC, isSelected = true
    )
}