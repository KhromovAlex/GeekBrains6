package com.example.gbapp6.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.example.gbapp6.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private var navController: NavController? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//
//    }
//
//    override fun onStart() {
//        super.onStart()
//
//        navController = findNavController(this, R.id.main_nav)
//
//        setupActionBarWithNavController(this, navController!!)
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return navController?.navigateUp() == true || super.onSupportNavigateUp()
//    }
}
