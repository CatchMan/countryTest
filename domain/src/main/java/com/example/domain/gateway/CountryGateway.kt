package com.example.domain.gateway

import com.catchman.domain.model.*
import com.example.domain.model.CountryInfo
import io.reactivex.Single



interface CountryGateway {

    fun getCountries(): Single<String>

    fun retrieveCountries(): Single<List<CountryInfo>>


}