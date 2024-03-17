package com.example.demojsonplaceholderapi.screens.fragment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import com.example.demojsonplaceholderapi.screens.activity.MainActivity
import com.example.demojsonplaceholderapi.utils.Constants
import com.example.demojsonplaceholderapi.utils.Utils
import com.example.demojsonplaceholderapi.viewmodel.MostPopularViewModel
import com.example.demojsonplaceholderapi.web.model.popular.MostPopularData
import com.example.demojsonplaceholderapi.web.model.popular.Result
import com.example.demorandomuserapi.ui.CustomHyperLinkText
import com.example.demorandomuserapi.ui.CustomNetworkImage
import com.example.demorandomuserapi.ui.CustomText
import com.example.demorandomuserapi.ui.LoadingAlertDialog
import com.example.demorandomuserapi.ui.theme.Purple500
import com.example.demorandomuserapi.ui.theme.WhiteContainer
import com.example.demorandomuserapi.ui.theme.boldFont
import kotlinx.coroutines.launch


@Composable
fun getMostPopularModel(mContext: MainActivity) {
    // Provide ViewModel
    val viewModel: MostPopularViewModel = hiltViewModel()
    callAPI(viewModel)
    val mostPopularData: State<MostPopularData?> =
        viewModel.getMostPopularData.collectAsState()

    ShowMostPopularData(mostPopularData, mContext)
}

@Composable
private fun ShowMostPopularData(mostPopularData: State<MostPopularData?>, mContext: MainActivity) {
    var isDialogVisible by remember { mutableStateOf(true) }

    if (mostPopularData.value == null || mostPopularData.value?.results.isNullOrEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            if (isDialogVisible) {
                LoadingAlertDialog(
                    onClose = { isDialogVisible = false }
                )
            }
            /* CustomText(
                 text = "Loading.......",
                 style = TextStyle(
                     fontFamily = normalFont,
                     fontWeight = FontWeight.Normal,
                     fontSize = 16.sp,
                 ),
                 textAlign = TextAlign.Center, color = Purple500
             )*/
        }
    } else if (mostPopularData.value?.results != null && mostPopularData.value!!.results.size > 0) {
        val length = mostPopularData.value?.results?.size
        println("LENGTH:>>>>>>> ${length}")
        LazyColumn {

            items(mostPopularData.value!!.results) { item: Result ->
                ShowMostPopularDataItem(mostPopularData = item, mContext = mContext)
            }
        }
    }
}

@Composable
fun ShowMostPopularDataItem(mostPopularData: Result, mContext: MainActivity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(10.dp)),
        backgroundColor = WhiteContainer
    ) {

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomNetworkImage(
                imageUrl = mostPopularData.media[0].media_metadata[0].url,
                contentDescription = "Sample image",
                modifier = Modifier
                    .fillMaxWidth() // Expand the image to match parent width
                    .clip(RoundedCornerShape(8.dp)),
                width = 0,
                height = 200,
                cornerRadius = 8,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                CustomText(
                    text = "Title :",
                    style = TextStyle(
                        fontFamily = boldFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    ),
                    textAlign = TextAlign.Start, color = Purple500
                )
                Spacer(modifier = Modifier.padding(end = 10.dp))
                CustomText(
                    text = mostPopularData.title,
                    style = TextStyle(
                        fontFamily = boldFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    ),
                    textAlign = TextAlign.Start, color = Purple500
                )
            }

            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                CustomText(
                    text = "ADX Keywords :",
                    style = TextStyle(
                        fontFamily = boldFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    ),
                    textAlign = TextAlign.Start, color = Purple500
                )
                Spacer(modifier = Modifier.padding(end = 10.dp))
                CustomText(
                    text = mostPopularData.adx_keywords,
                    style = TextStyle(
                        fontFamily = boldFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    ),
                    textAlign = TextAlign.Start, color = Purple500
                )
            }

            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
            ) {
                CustomText(
                    text = "Source :",
                    style = TextStyle(
                        fontFamily = boldFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    ),
                    textAlign = TextAlign.Start, color = Purple500
                )
                Spacer(modifier = Modifier.padding(end = 10.dp))
                CustomText(
                    text = mostPopularData.source,
                    style = TextStyle(
                        fontFamily = boldFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    ),
                    textAlign = TextAlign.Start, color = Purple500
                )
            }

            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            ) {
                CustomText(
                    text = "URL :",
                    style = TextStyle(
                        fontFamily = boldFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    ),
                    textAlign = TextAlign.Start, color = Purple500
                )
                Spacer(modifier = Modifier.padding(end = 10.dp))
                CustomHyperLinkText(
                    text = mostPopularData.url,
                    style = TextStyle(
                        fontFamily = boldFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    ),
                    textAlign = TextAlign.Start,
                    color = Color.Blue,
                    underlineColor = Color.Blue,
                    onClick = {
                        Utils.openAppFromLink(
                            mContext,
                            mostPopularData.url
                        )
                    },
                )
            }

            Spacer(modifier = Modifier.padding(top = 10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            ) {
                CustomText(
                    text = "Description :",
                    style = TextStyle(
                        fontFamily = boldFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                    ),
                    textAlign = TextAlign.Start, color = Purple500
                )
                Spacer(modifier = Modifier.padding(end = 10.dp))
                CustomText(
                    text = mostPopularData.abstract,
                    style = TextStyle(
                        fontFamily = boldFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                    ),
                    textAlign = TextAlign.Justify, color = Purple500
                )
            }
        }
    }

}

private fun callAPI(viewModel: MostPopularViewModel) {
    viewModel.viewModelScope.launch {
        viewModel.getMostPopularData(Constants.API_KEY)
    }
}