package com.developersancho.data.repository.di

import com.developersancho.data.remote.service.SearchService
import com.developersancho.data.repository.SearchRepository
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
    fun provideSearchRepository(
        service: SearchService
    ) = SearchRepository(service)
}