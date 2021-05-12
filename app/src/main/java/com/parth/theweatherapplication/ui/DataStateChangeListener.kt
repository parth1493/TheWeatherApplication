package com.parth.theweatherapplication.ui

interface DataStateChangeListener {
    fun onDataStateChange(dataState: DataState<*>?)
}