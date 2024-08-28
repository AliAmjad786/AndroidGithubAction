package com.example.graphqlapp.domain.usecases

import com.example.graphqlapp.domain.model.DetailCountry
import com.example.graphqlapp.domain.model.SimpleCountry
import com.example.graphqlapp.domain.repository.CountryRepo
import javax.inject.Inject

class GetCountryUseCase @Inject constructor(
    private val countryRepo: CountryRepo
) {
    suspend fun execute(code: String): DetailCountry? {
        return countryRepo.getCountry(code)
    }

}