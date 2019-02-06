package com.example.catchman.counrytz.ui.main

import com.example.catchman.counrytz.ui.base.IPresenter
import com.example.catchman.counrytz.ui.base.IView
import com.example.domain.model.CountryInfo

interface MainContract {

    interface View : IView {

        fun updateList(listCountryInfo: List<CountryInfo>)

    }

    interface Presenter<V : View> : IPresenter<V> {

        fun loadContent()

        fun retrieveCountries()

    }

}