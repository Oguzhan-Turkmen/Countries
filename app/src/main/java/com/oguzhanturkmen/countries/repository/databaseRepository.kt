package com.oguzhanturkmen.countries.repository

import com.oguzhanturkmen.countries.database.countriesDatabase
import com.oguzhanturkmen.countries.model.Countries.Country
import javax.inject.Inject

class databaseRepository @Inject constructor(val db: countriesDatabase) {
    suspend fun upsert(country: Country) = db.getCountriesDao().upsert(country)

    suspend fun getSavedCountries():List<Country>{
        val list =db.getCountriesDao().getAllCountries()
        return list
    }

    suspend fun deleteMovies(country: Country)= db.getCountriesDao().deleteMovie(country)
}