package com.emissa.apps.myweatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.emissa.apps.myweatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        navController = findNavController(R.id.nav_host_fragment_container)
        setupActionBarWithNavController(navController)
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        menuInflater.inflate(R.menu.menu, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.search) {
//            Log.d("Main activity", "Clicked on search icon")
//            Log.d("Main activity", "Setting activity's intent")
//            SearchDialog().show(supportFragmentManager, SearchDialog.DIALOG_TAG)
//            intent = item.intent
//        }
//        return super.onOptionsItemSelected(item)
//    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
