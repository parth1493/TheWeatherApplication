package com.parth.theweatherapplication.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.parth.theweatherapplication.R
import com.parth.theweatherapplication.databinding.FragmentMainBinding
import com.parth.theweatherapplication.model.SearchCurrentCityWeather
import com.parth.theweatherapplication.ui.BaseFragment
import com.parth.theweatherapplication.util.Constants.Companion.IMAGE_URL

class MainFragment : BaseFragment() {

    private var _binding: FragmentMainBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        subscribeObservers()
    }

    private fun subscribeObservers(){
        viewModel.dataState.observe(viewLifecycleOwner, Observer{ dataState ->
            stateChangeListener.onDataStateChange(dataState)
            if(dataState != null){
                dataState.data?.let { data ->
                    data.data?.let{ event ->
                        event.getContentIfNotHandled()?.let{ viewState ->
                            viewState.searchCurrentCityWeather?.let{ searchCurrentCityWeather ->
                                Log.d(TAG, "PARTH---2, DataState: ${searchCurrentCityWeather}")
                                viewModel.setSearchCurrentCityWeather(searchCurrentCityWeather)
                            }
                        }
                    }
                }
            }
        })

        viewModel.viewState.observe(viewLifecycleOwner, Observer{ viewState->
            if(viewState != null){
                viewState.searchCurrentCityWeather?.let{
                    Log.d(TAG, "PARTH---3, ViewState: ${it}")
                    setAccountDataFields(it)
                }
            }
        })
    }

    @SuppressLint("SetTextI18n")
    private fun setAccountDataFields(searchCurrentCityWeather: SearchCurrentCityWeather){
        Log.d("Parth--4", searchCurrentCityWeather.name+" , "+ searchCurrentCityWeather.icon)
        binding.todayIn.visibility = View.VISIBLE
        binding.cityName.visibility = View.VISIBLE
        binding.minMaxTemp.visibility = View.VISIBLE
        binding.current.visibility = View.VISIBLE
        binding.icon.visibility = View.VISIBLE

        binding.cityName.text = searchCurrentCityWeather.name
        binding.minMaxTemp.text = "max ${searchCurrentCityWeather.temp_max} / min ${searchCurrentCityWeather.temp_min}"
        binding.current.text = "Current ${searchCurrentCityWeather.temperature}"
        Glide.with(binding.icon.context)
            .asBitmap()
            .load("${IMAGE_URL}${searchCurrentCityWeather.icon}.png")
            .into(binding.icon)



    }
}