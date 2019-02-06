package com.example.catchman.counrytz.di.component

import android.app.Application
import android.content.Context
import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import com.example.catchman.counrytz.CountryApp
import com.example.domain.gateway.CountryGateway
import com.example.catchman.counrytz.di.ApplicationContext
import com.example.catchman.counrytz.di.modul.ApplicationModule
import dagger.Component
import javax.inject.Singleton



@Singleton
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

    fun inject(app: CountryApp)

    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread

    fun countryGateway(): CountryGateway

}