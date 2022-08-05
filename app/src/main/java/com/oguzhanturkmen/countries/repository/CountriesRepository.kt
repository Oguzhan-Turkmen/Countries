package com.oguzhanturkmen.countries.repository

import androidx.lifecycle.MutableLiveData
import com.oguzhanturkmen.countries.Model.Countries
import com.oguzhanturkmen.countries.Model.Country
import com.oguzhanturkmen.countries.api.RapidApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CountriesRepository @Inject constructor(private val rapidApi: RapidApi){

     fun getCountries(liveData: MutableLiveData<Countries>) {
        rapidApi.getCountries(offset = 0).enqueue(object : Callback<Countries>{
            override fun onResponse(call: Call<Countries>, response: Response<Countries>) {
                liveData.postValue(response.body())
            }
            override fun onFailure(call: Call<Countries>, t: Throwable) {
                liveData.postValue(null)
            }
        })
      }
    }
