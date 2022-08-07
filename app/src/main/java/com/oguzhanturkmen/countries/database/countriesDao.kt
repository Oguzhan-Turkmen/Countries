package com.oguzhanturkmen.countries.database

import androidx.room.*
import com.oguzhanturkmen.countries.model.Countries.Country
@Dao
interface countriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: Country):Long

    @Query("SELECT * FROM countries")
    suspend fun getAllCountries(): List<Country>

    @Delete
    suspend fun deleteMovie(country: Country)
}