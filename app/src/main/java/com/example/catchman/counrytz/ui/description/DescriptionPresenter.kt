package com.example.catchman.counrytz.ui.description

import android.util.Log
import android.widget.Toast
import com.example.catchman.counrytz.ui.base.BasePresenter
import com.example.catchman.counrytz.util.loge
import com.example.domain.model.CountryInfo
import io.reactivex.disposables.CompositeDisposable
import com.example.domain.interactor.countryTZ.*
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject


class DescriptionPresenter<V : DescriptionContract.View> @Inject constructor(val loadCountriesUseCase: LoadCountriesUseCase,
                                                                      compositeDisposable: CompositeDisposable) : BasePresenter<V>(compositeDisposable),
    DescriptionContract.Presenter<V> {



    override fun retrieveCountries(lst: String) {
        loadCountriesUseCase.execute(object : DisposableSingleObserver<List<CountryInfo>>() {
            override fun onSuccess(countries: List<CountryInfo>) {
                var list = ArrayList<String>()
                countries.forEach {
                    Log.e("!!!",  lst  + " - " + it.alpha3Code)
                    if(lst.contains(it.alpha3Code)){
                        list.add(it.nativeName)
                    }
                }
                view?.updateList(list)
            }

            override fun onError(e: Throwable) {
                "Cant load qr code from Storage".loge(e)
            }
        }, LoadCountriesUseCase.Params())

    }



}