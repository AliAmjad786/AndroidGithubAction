package com.example.graphqlapp.domain.repository

import com.example.graphqlapp.domain.model.DetailCountry
import com.example.graphqlapp.domain.model.SimpleCountry

interface CountryRepo {

    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailCountry?

}