package com.example.data.countries

import com.catchman.data.DataLogger
import com.example.domain.model.CountryInfo
import com.example.data.countries.service.CountryService
import com.example.domain.gateway.CountryGateway
import com.example.data.countries.storage.CountryStorage
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject



class CountryGatewayImpl @Inject constructor(val service: CountryService, val storage: CountryStorage, val logger: DataLogger) :
    CountryGateway {

    override fun getCountries(): Single<String> =
        service.getCountriesInfo()
            .flatMap {
                countries ->
                    Single.just(countries)
                        .flatMap(CountryFromServiceForStorage())
                        .flatMap { storage.putCountries(it) }
                        .subscribeOn(Schedulers.newThread())
            }


    override fun retrieveCountries(): Single<List<CountryInfo>> =
        storage.getAllCountries()
            .flatMapObservable { f -> Observable.fromIterable(f) }
            .map(CountryFromStorageToPresentation())
            .toList()

}