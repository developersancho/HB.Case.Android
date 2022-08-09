package com.developersancho.hb.di

import com.developersancho.hb.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Named("base-url")
    fun provideBaseUrl(): String = BuildConfig.BASE_URL
}