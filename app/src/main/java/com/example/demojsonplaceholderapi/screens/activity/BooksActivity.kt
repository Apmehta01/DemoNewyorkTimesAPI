package com.example.demojsonplaceholderapi.screens.activity

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.demorandomuserapi.ui.theme.DemoJsonPlaceHolderAPITheme
import com.example.demorandomuserapi.ui.theme.Purple500
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksActivity : ComponentActivity() {

    private val mContext = this@BooksActivity

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoJsonPlaceHolderAPITheme {

                Scaffold(topBar = {
                    TopAppBar(title = {
                        Text(text = "Books Data")
                    }, backgroundColor = Purple500, contentColor = Color.White)
                }) {
                    Box(
                        modifier = Modifier
                            .padding(it)
                            .background(Color.White)
                    ) {
                    }
                }
            }
        }
    }


}