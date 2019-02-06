package com.example.catchman.counrytz.di.modul

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.example.catchman.counrytz.util.dataLogger
import com.example.catchman.counrytz.util.domainLogger
import com.example.catchman.counrytz.util.netLogger
import com.catchman.data.DataLogger
import com.example.data.JobExecutor
import com.example.data.countries.CountryGatewayImpl
import com.example.data.countries.service.CountryApi
import com.example.data.countries.service.CountryServiceImpl
import com.example.domain.DomainLogger
import com.example.domain.executor.PostExecutionThread
import com.example.domain.executor.ThreadExecutor
import com.example.catchman.counrytz.UiThread
import com.example.data.ApiConst
import com.example.data.countries.service.CountryService
import com.example.domain.gateway.CountryGateway
import com.example.data.AppDatabase
import com.sfera.data.interceptor.LoggingInterceptor
import com.example.data.countries.storage.CountryStorage
import com.example.data.countries.storage.CountryStorageImpl
import com.example.catchman.counrytz.di.ApplicationContext
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class ApplicationModule(private val app: Application) {

    @Provides
    @ApplicationContext
    fun provideContext(): Context = app

    @Provides
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread = uiThread

    @Provides
    fun provideDataLogger(): DataLogger = dataLogger()

    @Provides
    fun provideDomainLogger(): DomainLogger = domainLogger()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {

        val logging = LoggingInterceptor(object : LoggingInterceptor.Logger {
            override fun log(message: String) = netLogger(message)
        }).setLevel(LoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideCountryApi(client: OkHttpClient): CountryApi {
        return Retrofit.Builder()
                .baseUrl(ApiConst.ENDPOINT)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(CountryApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSferaService(countryService: CountryServiceImpl): CountryService = countryService

    @Provides
    @Singleton
    fun provideSferaStorage(countryStorage: CountryStorageImpl): CountryStorage = countryStorage

    @Provides
    @Singleton
    fun provideSferaGateway(countryGateway: CountryGatewayImpl): CountryGateway = countryGateway

    @Provides
    @Singleton
    fun provideRoomDatabase() =
            Room.databaseBuilder(app.applicationContext, AppDatabase::class.java, "country-database")
                    .fallbackToDestructiveMigration()
                    .build()

}