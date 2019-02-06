package com.example.catchman.counrytz.ui.base

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.example.catchman.counrytz.util.logi
import com.example.catchman.counrytz.CountryApp
import com.example.catchman.counrytz.di.component.DaggerFragmentComponent
import com.example.catchman.counrytz.di.component.FragmentComponent
import com.example.catchman.counrytz.di.modul.ActivityModule
import com.example.catchman.counrytz.di.modul.FragmentModule



abstract class BaseFragment : Fragment(), IView {

    protected lateinit var fragmentComponent: FragmentComponent

    var activity: BaseActivity? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity){
            this.activity = context
            fragmentComponent = DaggerFragmentComponent.builder()
                    .fragmentModule(FragmentModule())
                    .activityModule(ActivityModule(context))
                    .applicationComponent((context.application as CountryApp).appComponent)
                    .build()
            context.onFragmentAttached()
        }
    }

    override fun onDetach() {
        activity = null
        super.onDetach()
    }

    override fun showProgress(show: Boolean) {
        if (isAdded) activity?.showProgress(show)
    }

    override fun isNetworkConnected(): Boolean = activity?.isNetworkConnected() ?: false

    override fun toast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    protected fun showDialog(title: String, message: String){
        AlertDialog.Builder(context!!)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok) { dialog, which ->  message.logi()}
                .show()
    }

    interface Callback {

        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }
}