package com.oguzhanturkmen.countries.api

import com.oguzhanturkmen.countries.model.Countries.Countries
import com.oguzhanturkmen.countries.model.Countries.Country
import com.oguzhanturkmen.countries.model.Country.CountryDetail
import com.oguzhanturkmen.countries.util.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RapidApi  {
    @GET("/v1/geo/countries")
    fun getCountries(@Query("offset") offset:Int , @Query("limit") limit: Int = 10, @Query("rapidapi-key") rapidapi_key:String= API_KEY):
            Call<Countries>

    @GET("/v1/geo/countries/{countryid}")
    fun getCountry(@Path("countryid") countryid:String, @Query("rapidapi-key") rapidapi_key:String= API_KEY): Call<CountryDetail>
}