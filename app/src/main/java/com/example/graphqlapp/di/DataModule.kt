package com.example.graphqlapp.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.example.graphqlapp.domain.repository.CountryRepo
import com.example.graphqlapp.domain.repository.CountryRepoImpl
import com.example.graphqlapp.domain.usecases.GetCountriesUseCase
import com.example.graphqlapp.domain.usecases.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .okHttpClient(OkHttpClient.Builder().build())
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryRepo(apolloClient: ApolloClient): CountryRepo {
        return CountryRepoImpl(apolloClient)
    }

    @Provides
    @Singleton
    fun providesGetCountriesUseCase(countryRepo: CountryRepo): GetCountriesUseCase {
        return GetCountriesUseCase(countryRepo)
    }

    @Provides
    @Singleton
    fun providesGetCountryUseCase(countryRepo: CountryRepo): GetCountryUseCase {
        return GetCountryUseCase(countryRepo)
    }
}