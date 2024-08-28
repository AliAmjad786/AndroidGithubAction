package com.example.graphqlapp.domain.model

data class DetailCountry(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String,
    val languages: List<String>,
    val continent: String
)
