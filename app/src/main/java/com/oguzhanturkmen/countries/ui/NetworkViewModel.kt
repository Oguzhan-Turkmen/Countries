package com.oguzhanturkmen.countries.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanturkmen.countries.model.Countries.Countries
import com.oguzhanturkmen.countries.model.Country.CountryDetail
import com.oguzhanturkmen.countries.model.Country.CountryDetailData
import com.oguzhanturkmen.countries.repository.CountriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(val repository: CountriesRepository): ViewModel() {
    val countriesList: MutableLiveData<Countries> = MutableLiveData()
    val countryDetailList: MutableLiveData<CountryDetail> = MutableLiveData()

    fun getObserverCountriesData():MutableLiveData<Countries>{
        return countriesList
    }
    fun loadCountries()=viewModelScope.launch {
        repository.getCountries(countriesList)
    }
    fun getObserverCountryDetail():MutableLiveData<CountryDetail>{
        return countryDetailList
    }
    fun loadCountryDetail(countryId:String)  {
         repository.getCountryDetail(countryId,countryDetailList)
    }

}