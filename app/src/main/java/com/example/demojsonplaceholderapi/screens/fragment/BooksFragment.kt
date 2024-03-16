package com.example.demojsonplaceholderapi.screens.fragment

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.example.demojsonplaceholderapi.viewmodel.BooksViewModel
import com.example.demojsonplaceholderapi.web.model.books.Book
import com.example.demojsonplaceholderapi.web.model.books.BooksData
import com.example.demorandomuserapi.ui.CustomHyperLinkText
import com.example.demorandomuserapi.ui.CustomNetworkImage
import com.example.demorandomuserapi.ui.CustomText
import com.example.demorandomuserapi.ui.theme.Purple500
import com.example.demorandomuserapi.ui.theme.WhiteContainer
import com.example.demorandomuserapi.ui.theme.boldFont
import com.example.demorandomuserapi.ui.theme.normalFont
import kotlinx.coroutines.launch


@Composable
fun getsBookViewModel(mContext: MainActivity) {
    // Provide ViewModel
    val viewModel: BooksViewModel = hiltViewModel()
    callAPI(viewModel)
    val booksData: State<BooksData?> =
        viewModel.getsBooksData.collectAsState()

    ShowBooksData(booksData, mContext)
}

@Composable
private fun ShowBooksData(booksData: State<BooksData?>, mContext: MainActivity) {
    if (booksData.value == null || booksData.value?.results?.books.isNullOrEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(1f),
            contentAlignment = Alignment.Center
        ) {
            CustomText(
                text = "Loading.......",
                style = TextStyle(
                    fontFamily = normalFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                ),
                textAlign = TextAlign.Center, color = Purple500
            )
        }
    } else if (booksData.value?.results != null && booksData.value!!.results.books.size > 0) {
        val length = booksData.value?.results?.books!!.size
        println("LENGTH:>>>>>>> ${length}")
        LazyColumn {
            items(booksData.value!!.results.books) { item: Book ->
                ShowBooksDataItem(booksData = item, mContext)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun ShowBooksDataItem(booksData: Book, mContext: MainActivity) {

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
                imageUrl = booksData.book_image,
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
                    text = booksData.title,
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
                    text = "Publisher :",
                    style = TextStyle(
                        fontFamily = boldFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                    ),
                    textAlign = TextAlign.Start, color = Purple500
                )
                Spacer(modifier = Modifier.padding(end = 10.dp))
                CustomText(
                    text = booksData.publisher,
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
                    text = "Price :",
                    style = TextStyle(
                        fontFamily = boldFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                    ),
                    textAlign = TextAlign.Start, color = Purple500
                )
                Spacer(modifier = Modifier.padding(end = 10.dp))
                CustomText(
                    text = booksData.price,
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
                    text = "Buy :",
                    style = TextStyle(
                        fontFamily = boldFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                    ),
                    textAlign = TextAlign.Start, color = Purple500
                )
                Spacer(modifier = Modifier.padding(end = 10.dp))
                CustomHyperLinkText(
                    text = booksData.amazon_product_url,
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
                            booksData.amazon_product_url
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
                    text = booksData.description,
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

private fun callAPI(viewModel: BooksViewModel) {
    viewModel.viewModelScope.launch {
        viewModel.getsBooksData(Constants.API_KEY)
    }
}