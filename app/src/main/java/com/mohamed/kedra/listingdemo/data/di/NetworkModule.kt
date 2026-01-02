package com.mohamed.kedra.listingdemo.data.di

import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideBaseUrl(): String = "https://apollo-fullstack-tutorial.herokuapp.com/graphql"

    @Provides
    @Singleton
    fun provideApolloClient(baseUrl: String): ApolloClient =
        ApolloClient.Builder()
            .serverUrl(baseUrl)
            .build()
}