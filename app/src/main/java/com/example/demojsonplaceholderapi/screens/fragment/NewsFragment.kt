package com.example.demojsonplaceholderapi.screens.fragment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.demojsonplaceholderapi.utils.Constants
import com.example.demojsonplaceholderapi.utils.Utils
import com.example.demojsonplaceholderapi.viewmodel.NewYorkTimesViewModel
import com.example.demojsonplaceholderapi.web.model.newyorktimes.NewyorkTimesData
import com.example.demojsonplaceholderapi.web.model.newyorktimes.Result
import com.example.demorandomuserapi.ui.CustomNetworkImage
import com.example.demorandomuserapi.ui.CustomText
import com.example.demorandomuserapi.ui.LoadingAlertDialog
import com.example.demorandomuserapi.ui.theme.Purple200
import com.example.demorandomuserapi.ui.theme.Purple500
import com.example.demorandomuserapi.ui.theme.Purple700
import com.example.demorandomuserapi.ui.theme.WhiteContainer
import com.example.demorandomuserapi.ui.theme.boldFont
import com.example.demorandomuserapi.ui.theme.normalFont
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun getNewsViewModel() {
    val viewModel: NewYorkTimesViewModel = hiltViewModel()
    callAPI(viewModel)
    val newYorkTimesData: State<NewyorkTimesData?> =
        viewModel.getNewYorkTimesData.collectAsState()

    ShowNewYorkTimesData(newYorkTimesData)
}

private fun callAPI(viewModel: NewYorkTimesViewModel) {
    viewModel.viewModelScope.launch {
        viewModel.getNewYorkTimesData(Constants.API_KEY)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun ShowNewYorkTimesData(newYorkTimesData: State<NewyorkTimesData?>) {
    var isDialogVisible by remember { mutableStateOf(true) }

    if (newYorkTimesData.value == null || newYorkTimesData.value?.results.isNullOrEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            if (isDialogVisible) {
                LoadingAlertDialog(
                    onClose = { isDialogVisible = false }
                )
            }
            /*            CustomText(
                            text = "Loading.......",
                            style = TextStyle(
                                fontFamily = normalFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                            ),
                            textAlign = TextAlign.Center, color = Purple500
                        )*/
        }
    } else if (newYorkTimesData.value?.results != null && newYorkTimesData.value!!.results.size > 0) {
        val length = newYorkTimesData.value?.results?.size
        println("LENGTH:>>>>>>> ${length}")
        LazyColumn {
            items(newYorkTimesData.value!!.results) { item: Result ->
                ShowNewYorkTimesItems(newYorkTimesData = item)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun ShowNewYorkTimesItems(newYorkTimesData: Result) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(0.dp)),
        backgroundColor = WhiteContainer
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomNetworkImage(
                imageUrl = newYorkTimesData.multimedia[0].url,
                contentDescription = "Sample image",
                modifier = Modifier
                    .fillMaxWidth() // Expand the image to match parent width
                    .clip(RoundedCornerShape(8.dp)),
                width = 0,
                height = 200,
                cornerRadius = 8,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp)) // Add space between the image and text
            CustomText(
                text = newYorkTimesData.title,
                style = TextStyle(
                    fontFamily = normalFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 20.sp,
                ),
                textAlign = TextAlign.Start,
                color = Purple500,
                modifier = Modifier.padding(start = 10.dp, end = 10.dp)
            )
            CustomText(
                text = newYorkTimesData.abstract,
                style = TextStyle(
                    fontFamily = normalFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                ),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                textAlign = TextAlign.Justify, color = Purple200
            )

            CustomText(
                text = Utils.formatDate(newYorkTimesData.published_date),
                style = TextStyle(
                    fontFamily = boldFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                ),
                modifier = Modifier.padding(start = 10.dp, end = 10.dp),
                textAlign = TextAlign.Justify, color = Purple700
            )
        }
    }
}