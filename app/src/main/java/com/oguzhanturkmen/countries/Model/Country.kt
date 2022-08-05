package com.oguzhanturkmen.countries.Model

data class Country(
    val code: String,
    val currencyCodes: List<String>,
    val name: String,
    val wikiDataId: String
)