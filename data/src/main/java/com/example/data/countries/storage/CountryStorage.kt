package com.example.data.countries.storage

import io.reactivex.Single




interface CountryStorage {

    fun putCountries(countries: List<StorageCountries>): Single<String>
    fun getAllCountries(): Single<List<StorageCountries>>

}