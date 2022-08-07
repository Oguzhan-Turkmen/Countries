package com.oguzhanturkmen.countries.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanturkmen.countries.model.Countries.Country
import com.oguzhanturkmen.countries.repository.databaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(val databaseRepo: databaseRepository): ViewModel(){

    val savedlivedata = MutableLiveData<List<Country>>()
    init {
            getSavedCountries()
    }
    fun saveCountry(country: Country) = viewModelScope.launch {
        databaseRepo.upsert(country)
    }
    fun getSavedCountries() {
        viewModelScope.launch {
            savedlivedata.postValue(databaseRepo.getSavedCountries())
        }
    }

    fun deleteMovies(country: Country) = viewModelScope.launch {
        databaseRepo.deleteMovies(country)
        getSavedCountries()
    }
}