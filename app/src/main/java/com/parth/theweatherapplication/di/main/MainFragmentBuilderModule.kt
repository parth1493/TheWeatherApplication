package com.parth.theweatherapplication.di.main

import com.parth.theweatherapplication.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainFragment(): MainFragment
}