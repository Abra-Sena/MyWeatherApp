package com.emissa.apps.myweatherapp.views

import androidx.fragment.app.Fragment
import com.emissa.apps.myweatherapp.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

open class BaseFragment : Fragment() {
    protected val weatherViewModel: WeatherViewModel by sharedViewModel()


//    fun handleIntent(intent: Intent) {
//        if (Intent.ACTION_SEARCH == intent.action) {
//            val query = intent.getStringExtra(SearchManager.QUERY).toString()
//            weatherViewModel.setCityName(query)
////            weatherViewModel.getForecast(query)
//        }
//    }
}