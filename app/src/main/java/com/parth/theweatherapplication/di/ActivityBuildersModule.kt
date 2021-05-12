package com.parth.theweatherapplication.di

import com.parth.theweatherapplication.di.main.MainFragmentBuilderModule
import com.parth.theweatherapplication.di.main.MainViewModeModule
import com.parth.theweatherapplication.di.main.weather.WeatherModule
import com.parth.theweatherapplication.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(modules = [MainFragmentBuilderModule::class, MainViewModeModule::class, WeatherModule::class])
    abstract fun contributeMainActivity(): MainActivity
}