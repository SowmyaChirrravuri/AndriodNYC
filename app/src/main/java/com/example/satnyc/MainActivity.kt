package com.example.satnyc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import com.example.satnyc.viewmodel.SchoolViewModel

import com.example.satnyc.ui.theme.SatNycTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<SchoolViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SatNycTheme {
                val navController = rememberNavController()
                SetupNavHost(navController = navController, viewModel = viewModel)
            }
        }
    }
}


