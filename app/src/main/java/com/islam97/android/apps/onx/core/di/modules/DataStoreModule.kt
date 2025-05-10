package com.islam97.android.apps.onx.core.di.modules

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    companion object {
        private const val LANGUAGE_DATA_STORE_NAME = "onx_language_data_store_preferences"
    }

    private val Context.languageDataStore by preferencesDataStore(name = LANGUAGE_DATA_STORE_NAME)

    @Singleton
    @Provides
    fun provideLanguageDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.languageDataStore
    }
}