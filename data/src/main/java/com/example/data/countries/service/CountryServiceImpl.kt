package com.example.data.countries.service

import io.reactivex.Single
import javax.inject.Inject



class CountryServiceImpl @Inject constructor(val countryApi: CountryApi): CountryService {

    override fun getCountriesInfo(): Single<List<CountryInfo>> = this.countryApi.geCounryInfo()
}