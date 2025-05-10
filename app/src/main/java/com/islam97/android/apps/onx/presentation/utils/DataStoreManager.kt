package com.islam97.android.apps.onx.presentation.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.islam97.android.apps.onx.presentation.language.Language
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LanguageDataStoreManager
@Inject constructor(private val languageDataStore: DataStore<Preferences>) {
    companion object {
        private val KEY_LANGUAGE = stringPreferencesKey("language")
    }

    suspend fun saveLanguage(language: Language) {
        languageDataStore.edit { preferences -> preferences[KEY_LANGUAGE] = language.name }
    }

    fun getLanguage(): Flow<Language> {
        return languageDataStore.data.map { preferences ->
            Language.valueOf(
                preferences[KEY_LANGUAGE] ?: Language.ARABIC.name
            )
        }
    }
}