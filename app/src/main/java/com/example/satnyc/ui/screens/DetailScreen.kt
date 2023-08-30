package com.example.satnyc.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.satnyc.data.SatScores
import com.example.satnyc.viewmodel.SchoolViewModel

@Composable
fun DetailScreen(dbn: String, navController: NavController, viewModel: SchoolViewModel) {
    viewModel.getSatScores(dbn)

    // Observe changes in viewState and update UI accordingly
    when (val satScoreState = viewModel.satScoreState.value) {
        is SchoolViewModel.ViewState.Loading -> {
            Text(text = "Loading...")
        }

        is SchoolViewModel.ViewState.Success<List<SatScores>> -> {
            schoolsDetail(satScoreState.data)

        }

        is SchoolViewModel.ViewState.Error -> {
            Text(text = "Error: ${satScoreState.message}")
        }
    }
}

@Composable
fun schoolsDetail(satScores: List<SatScores>?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Show SAT scores when available
        //If time has permitted should have used better styling and animation
        val satDetail = satScores?.firstOrNull()
        if (satDetail != null) {
            Text(text = "School Name: ${satDetail.school_name}")
            Text(text = "Number of SAT Test Takers: ${satDetail.num_of_sat_test_takers}")
            Text(text = "SAT Critical Reading Avg Score: ${satDetail.sat_critical_reading_avg_score}")
            Text(text = "SAT Math Avg Score: ${satDetail.sat_math_avg_score}")
            Text(text = "SAT Writing Avg Score: ${satDetail.sat_writing_avg_score}")
        } else {
            Text(text = "No data found for this school")
        }
    }
}

