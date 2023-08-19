package com.example.satnyc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.example.satnyc.VIEW.schoolItem
import com.example.satnyc.VIEWModel.schoolViewModel
import com.example.satnyc.dataclass.School

import com.example.satnyc.ui.theme.SatNycTheme

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<schoolViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SatNycTheme {
                schoolsList(schoolList = viewModel.schoolState.value)
                viewModel.getSchools()

            }
        }
    }


}

@Composable
fun schoolsList(schoolList: List<School>?) {
    LazyColumn {
        itemsIndexed(items = schoolList ?: emptyList()) { index,item ->
            schoolItem(item)
        }
    }
}




