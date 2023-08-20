package com.example.satnyc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.satnyc.VIEW.schoolItem
import com.example.satnyc.VIEWModel.schoolViewModel
import com.example.satnyc.dataclass.SatScores
import com.example.satnyc.dataclass.School

import com.example.satnyc.ui.theme.SatNycTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<schoolViewModel>()

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
fun HomeScreen(viewModel: schoolViewModel, navController: NavController) {
    viewModel.getSchools()
    schoolsList(schoolList = viewModel.schoolState.value, navController = navController)
}

@Composable
fun schoolsList(schoolList: List<School>?, navController: NavController) {
    LazyColumn {
        itemsIndexed(items = schoolList ?: emptyList()) { index, item ->
            schoolItem(item, navController)
        }
    }
}

@Composable
fun DetailScreen(dbn:String, navController:NavController, viewModel:schoolViewModel) {
    viewModel.getSatScores(dbn)
    Log.d("school", dbn)
    schoolsDetail(viewModel.satScoreState.value?.first())


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



