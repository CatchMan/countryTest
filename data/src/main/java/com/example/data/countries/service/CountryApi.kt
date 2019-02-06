package com.example.data.countries.service

import com.example.data.countries.service.CountryInfo
import io.reactivex.Single
import retrofit2.http.GET


interface CountryApi {

    @GET("rest/v2/all")
    fun geCounryInfo(): Single<List<CountryInfo>>

}