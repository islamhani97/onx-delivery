package com.islam97.android.apps.onx.core.di.modules

import com.islam97.android.apps.onx.data.repositories.AppRepositoryImpl
import com.islam97.android.apps.onx.domain.repositories.AppRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideAppRepository(repository: AppRepositoryImpl): AppRepository {
        return repository
    }
}