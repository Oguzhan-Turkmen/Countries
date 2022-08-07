package com.oguzhanturkmen.countries.repository

import androidx.lifecycle.MutableLiveData
import com.oguzhanturkmen.countries.model.Countries.Countries
import com.oguzhanturkmen.countries.api.RapidApi
import com.oguzhanturkmen.countries.model.Country.CountryDetail
import com.oguzhanturkmen.countries.model.Country.CountryDetailData
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

    fun getCountryDetail(counrtyId:String, liveData: MutableLiveData<CountryDetail>){
        rapidApi.getCountry(counrtyId).enqueue(object: Callback<CountryDetail>{
            override fun onResponse(call: Call<CountryDetail>, response: Response<CountryDetail>) {
                liveData.postValue(response.body())
            }
            override fun onFailure(call: Call<CountryDetail>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }
}
