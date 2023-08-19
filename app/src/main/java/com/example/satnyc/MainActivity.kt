package com.example.satnyc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.satnyc.VIEWModel.schoolViewModel
import com.example.satnyc.ui.theme.SatNycTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<schoolViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SatNycTheme {
                // A surface container using the 'background' color from the theme
              viewModel.getSchoolScores(id ="01M292")
            }
        }
    }
}



