package com.example.catchman.counrytz.di.component

import com.example.catchman.counrytz.di.PerFragment
import com.example.catchman.counrytz.di.modul.ActivityModule
import com.example.catchman.counrytz.di.modul.FragmentModule
import dagger.Component




@PerFragment
@Component(dependencies = [(ApplicationComponent::class)], modules = [(FragmentModule::class),(ActivityModule::class)])
interface FragmentComponent {

}