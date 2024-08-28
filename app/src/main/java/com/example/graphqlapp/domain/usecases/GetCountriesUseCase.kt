package com.example.graphqlapp.domain.usecases

import com.example.graphqlapp.domain.model.SimpleCountry
import com.example.graphqlapp.domain.repository.CountryRepo
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val countryRepo: CountryRepo
) {
    suspend fun execute(): List<SimpleCountry> {
        return countryRepo.getCountries().sortedBy { it.name }
    }

}