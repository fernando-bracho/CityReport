package com.example.cityreport

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cityreport.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflamos el layout de la actividad principal que contiene el FragmentContainerView
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}