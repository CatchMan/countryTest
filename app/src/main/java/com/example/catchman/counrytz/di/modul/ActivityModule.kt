package com.example.catchman.counrytz.di.modul

import android.support.v7.app.AppCompatActivity
import com.example.catchman.counrytz.ui.description.DescriptionContract
import com.example.catchman.counrytz.ui.description.DescriptionPresenter
import com.example.catchman.counrytz.ui.main.MainContract
import com.example.catchman.counrytz.ui.main.MainPresenter
import com.example.catchman.counrytz.di.ActivityContext
import com.example.catchman.counrytz.di.PerActivity
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityContext
    fun provideContext() = activity

    @Provides
    fun provideActivity() = activity

    @Provides
    fun provideCompositeDisposable() = CompositeDisposable()

    @Provides
    @PerActivity
    fun provideMainPresenter (mainPresenter: MainPresenter<MainContract.View>) :
            MainContract.Presenter<MainContract.View> = mainPresenter
    @Provides
    @PerActivity
    fun provideDescriptionPresenter (descriptionPresenter: DescriptionPresenter<DescriptionContract.View>) :
            DescriptionContract.Presenter<DescriptionContract.View> = descriptionPresenter
}