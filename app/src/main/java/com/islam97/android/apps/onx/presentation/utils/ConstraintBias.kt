package com.islam97.android.apps.onx.presentation.utils

import com.islam97.android.apps.onx.presentation.language.Language

fun Float.respectLanguage(currentLanguage: Language): Float {
    return when (currentLanguage) {
        Language.ARABIC -> 1f - this
        Language.ENGLISH -> this
    }
}