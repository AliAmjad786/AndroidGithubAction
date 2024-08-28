package com.example.graphqlapp.data.DataMapper

import com.example.graphqlapp.CountriesQuery
import com.example.graphqlapp.CountryQuery
import com.example.graphqlapp.domain.model.DetailCountry
import com.example.graphqlapp.domain.model.SimpleCountry


fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        name = name,
        code = code,
        emoji = emoji
    )
}

fun CountryQuery.Country.toDetailCountry(): DetailCountry {
    return DetailCountry(
        name = name,
        code = code,
        emoji = emoji,
        capital = capital ?: "No capital",
        languages = languages.mapNotNull { it.name },
        continent = continent.name
    )


}