package com.example.catchman.counrytz.ui.description

import com.example.catchman.counrytz.ui.base.IPresenter
import com.example.catchman.counrytz.ui.base.IView
import com.example.domain.model.CountryInfo

interface DescriptionContract {

    interface View : IView {

        fun updateList(listCountryInfo: List<String>)

    }

    interface Presenter<V : View> : IPresenter<V> {



        fun retrieveCountries(list: String)

    }

}