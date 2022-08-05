package com.oguzhanturkmen.countries.api

import com.oguzhanturkmen.countries.Model.Countries
import com.oguzhanturkmen.countries.util.API_KEY
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface RapidApi  {
    @GET("/v1/geo/countries")
    fun getCountries(@Query("offset") offset:Int , @Query("limit") limit: Int = 10, @Query("rapidapi-key") rapidapi_Key:String= API_KEY):
            Call<Countries>
}