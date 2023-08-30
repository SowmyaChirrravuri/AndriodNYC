package com.example.satnyc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.satnyc.Screens
import com.example.satnyc.viewmodel.SchoolViewModel
import com.example.satnyc.data.School

@Composable
fun HomeScreen(viewModel: SchoolViewModel, navController: NavController) {
    viewModel.getSchools()

    when (val schoolState = viewModel.schoolState.value) {
        is SchoolViewModel.ViewState.Loading -> {
            Text(text = "Loading...")
        }

        is SchoolViewModel.ViewState.Success<List<School>> -> {
            schoolsList(
                schoolList = schoolState.data,
                navController = navController,
                viewModel = viewModel
            )
        }

        is SchoolViewModel.ViewState.Error -> {
            Text(text = "Error: ${schoolState.message}")
        }
    }
}

@Composable
fun schoolsList(
    schoolList: List<School>?,
    navController: NavController,
    viewModel: SchoolViewModel
) {
    LazyColumn {
        itemsIndexed(items = schoolList ?: emptyList()) { index, item ->
            schoolItem(item, navController, viewModel)
        }
    }
}

@Composable
fun schoolItem(school: School, navController: NavController, viewModel: SchoolViewModel) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp)
            .clickable {
                navController.navigate(Screens.Detail.route + "/${school.dbn}")
            },
        shape = RoundedCornerShape(8.dp),
        elevation = 3.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxHeight()
        ) {
            school.dbn?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
            }
            school.school_name?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .background(Color.LightGray)
                        .padding(4.dp)
                )
            }
            school.buildingcode?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            school.total_students?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
            school.overview_paragraph?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}