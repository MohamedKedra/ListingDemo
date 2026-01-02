package com.mohamed.kedra.listingdemo.data.di

import com.apollographql.apollo3.ApolloClient
import com.mohamed.kedra.listingdemo.data.repository.LaunchesRepositoryImpl
import com.mohamed.kedra.listingdemo.domain.repository.LaunchesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(apolloClient: ApolloClient) : LaunchesRepository =
        LaunchesRepositoryImpl(apolloClient)
}