package com.keysharing.ui  // passe das Package auf deins an

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.keysharing.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BottomNavigationView aus dem Layout holen
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        // NavController vom NavHostFragment
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // BottomNav und NavController verbinden
        navView.setupWithNavController(navController)
    }
}
