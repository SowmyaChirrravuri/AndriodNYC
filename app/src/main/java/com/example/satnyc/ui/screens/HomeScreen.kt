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
import com.example.satnyc.dataclass.School

@Composable
fun HomeScreen(viewModel: SchoolViewModel, navController: NavController) {
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
fun schoolItem(school: School, navController: NavController) {
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