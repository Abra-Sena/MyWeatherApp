package com.emissa.apps.myweatherapp.views

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.emissa.apps.myweatherapp.R
import com.emissa.apps.myweatherapp.databinding.FragmentDialogSearchBinding
import com.emissa.apps.myweatherapp.viewmodel.WeatherViewModel

class SearchDialog : DialogFragment() {

    private val binding by lazy {
        FragmentDialogSearchBinding.inflate(layoutInflater)
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return binding.root
//    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.search_city))
            .setView(binding.root)
            .setNegativeButton("CANCEL", null)
            .setPositiveButton("OK") { _, _ ->
                Log.d("SearchDialog", "Clicked on positive button")
                val cityInputted = binding.etSearchInput.text.toString()

                activity?.intent?.putExtra(INTENT_EXTRA, cityInputted)

                Log.d("SearchDialog", "Retrieved user's input: $cityInputted")
                requireActivity().intent = Intent(Intent.ACTION_SEARCH).apply {
                    this.putExtra(INTENT_EXTRA, cityInputted)
                }
                Log.d("SearchDialog", "Set intent to: ${activity?.intent}")
                dismiss()
            }
            .create()


    companion object {
        const val INTENT_EXTRA = "SearchQuery"
        const val DIALOG_TAG = "Search"
    }
}