package com.oguzhanturkmen.countries

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanturkmen.countries.Model.Countries
import com.oguzhanturkmen.countries.repository.CountriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NetworkViewModel @Inject constructor(val repository: CountriesRepository): ViewModel() {
    val countriesList: MutableLiveData<Countries> = MutableLiveData()

    fun getObserverCountriesData():MutableLiveData<Countries>{
        return countriesList
    }
    fun loadCountries()=viewModelScope.launch {
        repository.getCountries(countriesList)
    }

}