package com.oguzhanturkmen.countries.di

import com.oguzhanturkmen.countries.api.RapidApi
import com.oguzhanturkmen.countries.util.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun getRetrofitService(retrofit: Retrofit): RapidApi {
        return retrofit.create(RapidApi::class.java)
    }
    @Singleton
    @Provides
    fun getRetrofitInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    @Singleton
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}