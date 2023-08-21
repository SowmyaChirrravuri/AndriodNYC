package com.example.satnyc.ui.screens

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.satnyc.viewmodel.SchoolViewModel
import com.example.satnyc.schoolsDetail

@Composable
fun DetailScreen(dbn:String, navController: NavController, viewModel: SchoolViewModel) {
    viewModel.getSatScores(dbn)
    Log.d("school", dbn)
    viewModel.satScoreState.value?.firstOrNull()?.let { firstSatScore ->
        schoolsDetail(firstSatScore)
    }?: run {
//       val coroutineScope = rememberCoroutineScope()
//        // Display a toast message here
//        Toast.makeText(LocalContext.current, "No SAT scores available.", Toast.LENGTH_SHORT).show()
    }


}