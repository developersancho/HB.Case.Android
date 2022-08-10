package com.developersancho.domain.di

import com.developersancho.data.repository.SearchRepository
import com.developersancho.domain.search.Search
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Singleton
    @Provides
    fun provideSearch(repository: SearchRepository): Search {
        return Search(repository)
    }
}