package com.example.satnyc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.satnyc.viewmodel.SchoolViewModel
import com.example.satnyc.dataclass.SatScores

import com.example.satnyc.ui.theme.SatNycTheme

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


@Composable
fun schoolsDetail(satDetail: SatScores?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "School Name: ${satDetail?.school_name}")
        Text(text = "Number of SAT Test Takers: ${satDetail?.num_of_sat_test_takers}")
        Text(text = "SAT Critical Reading Avg Score: ${satDetail?.sat_critical_reading_avg_score}")
        Text(text = "SAT Math Avg Score: ${satDetail?.sat_math_avg_score}")
        Text(text = "SAT Writing Avg Score: ${satDetail?.sat_writing_avg_score}")

    }
}



