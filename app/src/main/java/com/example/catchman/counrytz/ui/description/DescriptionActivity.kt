package com.example.catchman.counrytz.ui.description

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.catchman.counrytz.R
import com.example.catchman.counrytz.ui.base.BaseActivity
import com.example.catchman.counrytz.ui.main.MainContract
import com.example.domain.model.CountryInfo
import kotlinx.android.synthetic.main.activity_description.*
import javax.inject.Inject

class DescriptionActivity : BaseActivity(), DescriptionContract.View {

    companion object {

        private val EXTRA_KEY_BORDER = "border"

        fun start(context: Context, border: String) {
            val starter = Intent(context, DescriptionActivity::class.java)
            starter.putExtra(EXTRA_KEY_BORDER, border)
            starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(starter)
        }
    }

    @Inject
    lateinit var presenter: DescriptionContract.Presenter<DescriptionContract.View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        activityComponent.inject(this)
        presenter.bindView(this)
        presenter.retrieveCountries(intent.getStringExtra(EXTRA_KEY_BORDER))

    }

    override fun updateList(listCountryInfo: List<String>) {
        if(listCountryInfo.isEmpty()){
            tvBorders.text = getString(R.string.has_not_neighbor)
        } else {
            tvBorders.text = getString(R.string.country_neighbors)
            listCountryInfo.forEach {
                tvBorders.text = tvBorders.text.toString() + "\n" + it
             }
        }

    }
}
