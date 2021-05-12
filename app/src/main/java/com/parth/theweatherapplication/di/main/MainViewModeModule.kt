package com.parth.theweatherapplication.di.main

import androidx.lifecycle.ViewModel
import com.parth.theweatherapplication.di.ViewModelKey
import com.parth.theweatherapplication.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModeModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainVIewModel(mainViewModel: MainViewModel):ViewModel
}