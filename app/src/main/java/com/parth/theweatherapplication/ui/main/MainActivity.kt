package com.parth.theweatherapplication.ui.main

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.parth.theweatherapplication.BuildConfig
import com.parth.theweatherapplication.R
import com.parth.theweatherapplication.databinding.ActivityMainBinding
import com.parth.theweatherapplication.databinding.ContentMainBinding
import com.parth.theweatherapplication.ui.BaseActivity
import com.parth.theweatherapplication.ui.main.state.MainStateEvent
import com.parth.theweatherapplication.ui.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private lateinit var _binding: ActivityMainBinding
    private lateinit var _bindingContent:ContentMainBinding

    private val binding get() = _binding
    private val bindingContent get() = _bindingContent

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        _bindingContent = ContentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.searchEditText.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH){
                doSomething()
                true
            } else {
                false
            }
        }

        viewModel = ViewModelProvider(this, providerFactory).get(MainViewModel::class.java)

        initFragment()

    }

    private fun initFragment(){

        val textFragment = MainFragment()
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.main_fragment,textFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
    private fun doSomething(){
        Log.d("parth: ",binding.searchEditText.text.toString())

        viewModel.setStateEvent(
           MainStateEvent.SearchCurrentCityEvent(
               binding.searchEditText.text.toString()
           )
        )
    }

    override fun onStart() {
        super.onStart()
        viewModel.setStateEvent(
            MainStateEvent.CheckPreviousWeatherData()
        )
    }

    override fun displayProgressBar(bool: Boolean) {
        if(bool){
           bindingContent.progressBar.visibility = View.VISIBLE
        }
        else{
            bindingContent.progressBar.visibility = View.GONE
        }
    }
}