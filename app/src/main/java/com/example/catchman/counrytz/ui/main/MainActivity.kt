package com.example.catchman.counrytz.ui.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.catchman.counrytz.ui.base.BaseActivity
import com.example.domain.model.CountryInfo
import com.example.catchman.counrytz.R
import com.example.catchman.counrytz.ui.description.DescriptionActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View, View.OnClickListener {

    private var countriesModel: List<CountryInfo> ?= null
    private var countryAdapter: CountryAdapter ?= null
    @Inject
    lateinit var presenter: MainContract.Presenter<MainContract.View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activityComponent.inject(this)
        presenter.bindView(this)
    }

    override fun onStart() {
        super.onStart()
        countryAdapter = CountryAdapter(this)
        rvCountry.layoutManager = LinearLayoutManager(this)
        rvCountry.adapter = countryAdapter
        showProgress(true)
        presenter.loadContent()
    }

    override fun updateList(listCountryInfo: List<CountryInfo>) {
        showProgress(false)
        countryAdapter?.updateData(listCountryInfo)
        countriesModel = listCountryInfo
    }

    override fun onClick(v: View) {
        var position: Int = rvCountry!!.getChildAdapterPosition(v)
        DescriptionActivity.start(this, countriesModel!![position].borders)
    }
}
