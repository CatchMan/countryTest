package com.example.data.countries.service

import io.reactivex.Single


/**
 * Created by ujujzk on 21.06.2018
 * Softensy Digital Studio
 * softensiteam@gmail.com
 */

interface CountryService {

    fun getCountriesInfo(): Single<List<CountryInfo>>

}