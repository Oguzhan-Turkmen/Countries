package com.oguzhanturkmen.countries.model.Country

data class CountryDetailData(
    val callingCode: String,
    val capital: String,
    val code: String,
    val currencyCodes: List<String>,
    val flagImageUri: String,
    val name: String,
    val numRegions: Int,
    val wikiDataId: String
)