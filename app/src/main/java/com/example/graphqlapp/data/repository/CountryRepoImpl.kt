package com.example.graphqlapp.domain.repository

import com.apollographql.apollo.ApolloClient
import com.example.graphqlapp.CountriesQuery
import com.example.graphqlapp.CountryQuery
import com.example.graphqlapp.data.DataMapper.toDetailCountry
import com.example.graphqlapp.data.DataMapper.toSimpleCountry
import com.example.graphqlapp.domain.model.DetailCountry
import com.example.graphqlapp.domain.model.SimpleCountry
import javax.inject.Inject

class CountryRepoImpl @Inject constructor(val apolloClient: ApolloClient) : CountryRepo {
    override suspend fun getCountries(): List<SimpleCountry> {

        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data?.countries?.map {
                it.toSimpleCountry()
            } ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailCountry? {

        return apolloClient.query(CountryQuery(code))
            .execute()
            .data?.country?.toDetailCountry()
    }

}

