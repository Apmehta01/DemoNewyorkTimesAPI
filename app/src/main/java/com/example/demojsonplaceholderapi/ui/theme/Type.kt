package com.example.demorandomuserapi.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.demojsonplaceholderapi.R

val lightFont = FontFamily(
    Font(R.font.poppins_thin, FontWeight.Light)
)

val normalFont = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal)
)

val boldFont = FontFamily(
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val extraBoldFont = FontFamily(
    Font(R.font.poppins_extra_bold, FontWeight.ExtraBold)
)
// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
)