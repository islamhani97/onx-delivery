package com.islam97.android.apps.onx.presentation.language

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.islam97.android.apps.onx.R
import com.islam97.android.apps.onx.presentation.ui.theme.appColorScheme
import com.islam97.android.apps.onx.presentation.ui.theme.appShapes

@Composable
fun ChangeLanguageDialog(
    selectedLanguage: Language, onSelectLanguage: (language: Language) -> Unit, onApply: () -> Unit
) {
    AlertDialog(onDismissRequest = {}, title = {
        Text(
            text = stringResource(R.string.title_choose_language),
            style = LocalTextStyle.current.copy(color = MaterialTheme.appColorScheme.primary)
        )
    }, text = {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            LanguageOption(modifier = Modifier, onClick = {
                onSelectLanguage(Language.ARABIC)
            }, Language.ARABIC, Language.ARABIC == selectedLanguage)

            LanguageOption(modifier = Modifier, onClick = {
                onSelectLanguage(Language.ENGLISH)
            }, Language.ENGLISH, Language.ENGLISH == selectedLanguage)
        }
    }, confirmButton = {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onApply() },
            shape = MaterialTheme.appShapes.languageOptionShape,
        ) {
            Text(stringResource(R.string.apply))
        }
    })
}

@Preview(showBackground = true)
@Composable
fun PreviewChangeLanguageDialog() {
    ChangeLanguageDialog(Language.ARABIC, {}, {})
}

enum class Language(
    val textResourceId: Int, val iconResourceId: Int, val number: String, val code: String
) {
    ARABIC(
        textResourceId = R.string.language_arabic,
        iconResourceId = R.drawable.ic_language_arabic,
        number = "1",
        code = "ar"
    ),
    ENGLISH(
        textResourceId = R.string.language_english,
        iconResourceId = R.drawable.ic_language_english,
        number = "2",
        code = "en"
    )
}