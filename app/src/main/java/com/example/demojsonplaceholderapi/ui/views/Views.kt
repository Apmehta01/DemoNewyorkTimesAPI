package com.example.demorandomuserapi.ui


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun CustomText(
    text: String,
    style: TextStyle,
    textAlign: TextAlign,
    color: Color,
    modifier: Modifier = Modifier

) {
    Text(
        text = text,
        style = style,
        textAlign = textAlign,
        modifier = modifier,
        color = color
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun CustomHyperLinkText(
    text: String,
    style: TextStyle,
    textAlign: TextAlign,
    color: Color,
    underlineColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Text(
            text = text,
            style = style,
            textAlign = textAlign,
            color = color,
            modifier = Modifier.clickable(onClick = onClick)
        )
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .align(Alignment.BottomStart)
        ) {
            drawLine(
                color = underlineColor,
                start = Offset.Zero,
                end = Offset(size.width, 0f),
                strokeWidth = 2.dp.toPx(),
                cap = StrokeCap.Round
            )
        }
    }
}

@Composable
fun CustomImage(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    width: Int,
    height: Int,
    cornerRadius: Int,
    contentScale: ContentScale
) {
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .size(width.dp, height.dp)
            .clip(RoundedCornerShape(cornerRadius.dp)),
        contentScale = contentScale
    )
}


@Composable
fun CustomNetworkImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    width: Int,
    height: Int,
    cornerRadius: Int,
    contentScale: ContentScale
) {
    val imagePainter = rememberImagePainter(
        data = imageUrl,
        builder = {
            // Add any additional configurations if needed
        }
    )

    Box(
        modifier = modifier
            .size(width.dp, height.dp)
            .clip(RoundedCornerShape(cornerRadius.dp))
    ) {
        Image(
            painter = imagePainter,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize(),
            contentScale = contentScale,
            alignment = Alignment.Center
        )
    }
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape,
    textStyle: TextStyle = MaterialTheme.typography.button,
    buttonColor: Color,
    borderColor: Color,
    borderWidth: Dp = 1.dp,
    textColor: Color,
    contentPadding: PaddingValues
) {
    Button(
        onClick = onClick,
        modifier = modifier
//            .border(width = borderWidth, color = borderColor, shape = shape)
            .background(color = Color.Transparent, shape = shape),
        shape = shape,
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor), // Set transparent background color,
        contentPadding = contentPadding,
        content = {
            CustomText(
                text = text,
                style = textStyle,
                textAlign = TextAlign.Center,
                color = textColor,
                modifier = Modifier.fillMaxWidth()
            )
        }
    )
}

@Composable
fun CustomTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(4.dp),
    textStyle: TextStyle = MaterialTheme.typography.button,
    buttonColor: Color = Color.Transparent,
    borderColor: Color = Color.Black,
    borderWidth: Dp = 1.dp,
    textColor: Color,
    contentPadding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .background(color = buttonColor, shape = shape),
        shape = shape,
        contentPadding = contentPadding,
        content = {
            Text(
                text = text,
                style = textStyle,
                textAlign = TextAlign.Center,
                color = textColor,
                modifier = Modifier.fillMaxWidth()
            )
        }
    )
}

@Composable
fun CustomOutlineButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(4.dp),
    textStyle: TextStyle = MaterialTheme.typography.button,
    buttonColor: Color = Color.Transparent,
    borderColor: Color = Color.Black,
    borderWidth: Dp = 1.dp,
    textColor: Color,
    contentPadding: PaddingValues = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
) {
    TextButton(
        onClick = onClick,
        modifier = modifier
            .background(color = buttonColor, shape = shape)
            .border(width = borderWidth, color = borderColor, shape = shape),
        shape = shape,
        contentPadding = contentPadding,
        content = {
            Text(
                text = text,
                style = textStyle,
                textAlign = TextAlign.Center,
                color = textColor,
                modifier = Modifier.fillMaxWidth()
            )
        }
    )
}

@Composable
fun CustomIcon(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = Color.Unspecified
) {
    Icon(
        imageVector = imageVector,
        contentDescription = contentDescription,
        modifier = modifier,
        tint = tint
    )
}

@Composable
fun CustomIconButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape,
    textStyle: TextStyle = MaterialTheme.typography.button,
    buttonColor: Color,
    borderColor: Color,
    borderWidth: Dp = 0.dp,
    textColor: Color,
    contentPadding: PaddingValues,
    imagePath: ImageVector,
    iconColor: Color
) {
    Button(
        onClick = onClick,
        modifier = modifier
//            .border(width = borderWidth, color = borderColor, shape = shape)
            .background(color = Color.Transparent, shape = shape),
        shape = shape,
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor), // Set transparent background color,
        contentPadding = contentPadding,
        content = {
            Row {
                CustomIcon(
                    imageVector = imagePath,
                    modifier = Modifier.padding(end = 5.dp),
                    contentDescription = null,
                    tint = iconColor
                )
                Spacer(modifier = Modifier.width(4.dp))
                CustomText(
                    text = text,
                    style = textStyle,
                    textAlign = TextAlign.Center,
                    color = textColor,
//                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    )
}

@Composable
fun CustomTwoIconButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape,
    textStyle: TextStyle = MaterialTheme.typography.button,
    buttonColor: Color,
    borderColor: Color,
    borderWidth: Dp = 0.dp,
    textColor: Color,
    contentPadding: PaddingValues,
    imagePath: ImageVector,
    iconColor: Color
) {
    Button(
        onClick = onClick,
        modifier = modifier
//            .border(width = borderWidth, color = borderColor, shape = shape)
            .background(color = Color.Transparent, shape = shape),
        shape = shape,
        colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor), // Set transparent background color,
        contentPadding = contentPadding,
        content = {
            Row {
                CustomIcon(
                    imageVector = imagePath,
                    modifier = Modifier.padding(end = 5.dp),
                    contentDescription = null,
                    tint = iconColor
                )
                Spacer(modifier = Modifier.width(4.dp))
                CustomText(
                    text = text,
                    style = textStyle,
                    textAlign = TextAlign.Center,
                    color = textColor,
//                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.width(4.dp))
                CustomIcon(
                    imageVector = imagePath,
                    modifier = Modifier.padding(start = 5.dp),
                    contentDescription = null,
                    tint = iconColor
                )
            }

        }
    )
}

@Composable
fun CustomGradientHorizontalButton(
    modifier: Modifier = Modifier,
    horizontalGradientBrush: Brush,
    text: String,
    textColor: Color,
    textStyle: TextStyle = MaterialTheme.typography.button,
    onClick: () -> Unit,
    width: Dp = ButtonDefaults.MinWidth,
    paddingValues: PaddingValues,
    textAlign: TextAlign

) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(brush = horizontalGradientBrush)
            .clickable(onClick = onClick)
            .width(width)
            .then(modifier),
//            .padding(paddingValues),
//        horizontalAlignment = Alignment.CenterHorizontally // Center align column content// Apply padding
    ) {
        CustomText(
            text = text,
            style = textStyle,
            textAlign = textAlign,
            color = textColor,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally) // Center the text within the column
        )
    }
}

@Composable
fun CustomGradientVerticalButton(
    modifier: Modifier = Modifier,
    verticalGradientBrush: Brush,
    text: String,
    textColor: Color,
    textStyle: TextStyle = MaterialTheme.typography.button,
    onClick: () -> Unit,
    width: Dp = ButtonDefaults.MinWidth,
    paddingValues: PaddingValues,
    textAlign: TextAlign

) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(brush = verticalGradientBrush)
            .clickable(onClick = onClick)
            .width(width)
            .then(modifier),
//            .padding(paddingValues),
//        horizontalAlignment = Alignment.CenterHorizontally // Center align column content// Apply padding
    ) {
        CustomText(
            text = text,
            style = textStyle,
            textAlign = textAlign,
            color = textColor,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally) // Center the text within the column
        )
    }
}

@Composable
fun CustomIconToggleButton(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    checkedIcon: ImageVector,
    uncheckedIcon: ImageVector,
    checkedTint: Color,
    uncheckedTint: Color,
    contentDescription: String?,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    iconColor: Color,
    onClick: () -> Unit
) {
    IconToggleButton(
        modifier = modifier,
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        interactionSource = interactionSource,
        content = {
            if (checked) {
                CustomIcon(
                    imageVector = checkedIcon,
                    modifier = Modifier.padding(start = 5.dp),
                    contentDescription = null,
                    tint = checkedTint
                )
            } else {
                CustomIcon(
                    imageVector = uncheckedIcon,
                    modifier = Modifier.padding(start = 5.dp),
                    contentDescription = null,
                    tint = uncheckedTint
                )
            }
        },
    )
}

@Composable
fun CustomFloatingActionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentColor: Color = MaterialTheme.colors.onPrimary,
    backgroundColor: Color = MaterialTheme.colors.primary,
    contentDescription: String? = null,
    imagePath: ImageVector,
    iconColor: Color
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd
    ) {
        FloatingActionButton(
            onClick = onClick,
            modifier = modifier,
            contentColor = contentColor,
            backgroundColor = backgroundColor,
        ) {
            CustomIcon(
                imageVector = imagePath,
                modifier = Modifier.padding(start = 5.dp),
                contentDescription = null,
                tint = iconColor
            )
        }
    }
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingAlertDialog(
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onClose() },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier.padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "Loading GIF",
                    modifier = Modifier.size(100.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(16.dp))
                // Text
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
        }
    }
}*/
