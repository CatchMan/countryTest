package com.example.catchman.counrytz.di.component

import com.example.catchman.counrytz.ui.description.DescriptionActivity
import com.example.catchman.counrytz.ui.main.MainActivity
import com.example.catchman.counrytz.di.PerActivity
import com.example.catchman.counrytz.di.modul.ActivityModule
import dagger.Component




@PerActivity
@Component(dependencies = [(ApplicationComponent::class)], modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(descriptionActivity: DescriptionActivity)

}