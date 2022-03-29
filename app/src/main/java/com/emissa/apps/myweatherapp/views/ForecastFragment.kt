package com.emissa.apps.myweatherapp.views

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.emissa.apps.myweatherapp.R
import com.emissa.apps.myweatherapp.adapter.ItemClicked
import com.emissa.apps.myweatherapp.adapter.WeatherAdapter
import com.emissa.apps.myweatherapp.databinding.FragmentForecastBinding
import com.emissa.apps.myweatherapp.model.Forecast
import com.emissa.apps.myweatherapp.viewmodel.ResultState

class ForecastFragment : BaseFragment(), ItemClicked {

    private val binding by lazy {
        FragmentForecastBinding.inflate(layoutInflater)
    }

    private val weatherAdapter by lazy {
        WeatherAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.forecastRecycler.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = weatherAdapter
        }
        weatherViewModel.cityForecast.observe(viewLifecycleOwner, ::handleState)

        Log.d("AfterObserve:", " Before Intent Call")

//        val activityIntent = activity?.intent
//        Log.d("IntentCalled:", "Activity's intent: $activityIntent")
//        if (activityIntent != null) {
//            if (activityIntent.hasExtra(SearchDialog.INTENT_EXTRA))
//        }

        weatherViewModel.setCityName("Atlanta")
        val city = weatherViewModel.getCity()
        Log.d("IntentCalled:", "Activity's city set to: $city")
        binding.city.text = "$city forecast"
        weatherViewModel.getForecast(city)
//        weatherViewModel.getForecast(weatherViewModel.getCity())
//        if (activityIntent != null) {
//            weatherViewModel.getCity()
//            if (activityIntent.hasExtra(SearchDialog.INTENT_EXTRA)) {
//                val cityQuery = activityIntent.extras.toString()
//                Log.d("IntentCalled:", "Action search - user query: $cityQuery")
//                weatherViewModel.setCityName(cityQuery)
//                Log.d("IntentCalled:", "Action search-forecast retrieved for city user entered: $cityQuery")
//            }
//        } else {
//            weatherViewModel.setCityName("Atlanta")
//        }


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.search) {
            Log.d("FragmentMenuItemSelect", "Clicked on search icon")
            SearchDialog().show(childFragmentManager, SearchDialog.DIALOG_TAG)
            val intent = requireActivity().intent
            Log.d("FragmentMenuItemSelect", "Set activity's intent to: $intent")

            val locationQuery = intent.extras.toString()
            Log.d("IntentCalled:", "Action search - user query: $locationQuery")
            weatherViewModel.setCityName(locationQuery)
            Log.d("IntentCalled:", "Action search-forecast retrieved for city user entered: $locationQuery")
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        findNavController().navigateUp()
    }

    override fun onItemClicked(forecast: Forecast) {
        weatherViewModel.setForecast(forecast)
        findNavController().navigate(R.id.action_ForecastFragment_to_ForecastDetailsFragment)
    }

    private fun handleState(resultState: ResultState) {
        when(resultState) {
            is ResultState.LOADING -> {
                Toast.makeText(requireContext(), "LOADING...", Toast.LENGTH_LONG).show()
            }
            is ResultState.SUCCESS -> {
                weatherAdapter.setForecast(resultState.results.list)
            }
            is ResultState.ERROR -> {
                Log.e("Forecast", resultState.error.localizedMessage)
                Toast.makeText(
                    requireContext(),
                    resultState.error.localizedMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}