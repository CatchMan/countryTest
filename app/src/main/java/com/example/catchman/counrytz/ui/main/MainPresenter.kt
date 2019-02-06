package com.example.catchman.counrytz.ui.main

import com.example.catchman.counrytz.ui.base.BasePresenter
import com.example.catchman.counrytz.util.loge
import com.example.catchman.counrytz.util.logi
import com.example.domain.model.CountryInfo
import io.reactivex.disposables.CompositeDisposable
import com.example.domain.interactor.countryTZ.*
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject


class MainPresenter<V : MainContract.View> @Inject constructor(val getCountriesUseCase: GetCountriesUseCase,
                                                               val loadCountriesUseCase: LoadCountriesUseCase,
                                                               compositeDisposable: CompositeDisposable) : BasePresenter<V>(compositeDisposable), MainContract.Presenter<V> {

    init {
        compositeDisposable.addAll(getCountriesUseCase)
    }

    override fun loadContent() {
        getCountriesUseCase.execute(object: DisposableSingleObserver<String>(){
            override fun onSuccess(t: String) {
                retrieveCountries()
                t.logi()
            }

            override fun onError(e: Throwable) {
                "Cant update Content".loge(e)
            }
        }, GetCountriesUseCase.Params())
    }

    override fun retrieveCountries() {
        loadCountriesUseCase.execute(object : DisposableSingleObserver<List<CountryInfo>>() {
            override fun onSuccess(countries: List<CountryInfo>) {
                view?.updateList(countries)
            }

            override fun onError(e: Throwable) {
                "Cant load qr code from Storage".loge(e)
            }
        }, LoadCountriesUseCase.Params())

    }



}