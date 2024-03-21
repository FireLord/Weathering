package com.firelord.weathering.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.firelord.weathering.R
import com.firelord.weathering.presentation.ui.theme.ColorGirlDark
import com.firelord.weathering.presentation.ui.theme.GirlGrey
import com.firelord.weathering.presentation.ui.theme.WeatheringTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatheringTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        Modifier
                            .size(250.dp)
                    ) {
                        Text(
                            "Weathering",
                            fontSize = 36.sp,
                            color = ColorGirlDark,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(top = 50.dp)
                        )

                        Image(
                            painter = painterResource(id = R.drawable.girl),
                            contentDescription = "Weathering",
                            modifier = Modifier
                                .align(Alignment.Center)
                        )

                        Column (
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 50.dp)
                        ) {
                            Text(
                                text = "Hello,",
                                color = ColorGirlDark,
                                fontSize = 36.sp,
                                modifier = Modifier
                                    .align(Alignment.Start)
                            )

                            Text(
                                text = "Enter your location to get started",
                                color = GirlGrey,
                                fontSize = 14.sp,
                            )

                            LocationTextField(
                                text = "",
                                onTextChange = {},
                                onSearchClick = { /*TODO*/ })

                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LocationTextField(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChange,
        placeholder = { Text(text = "Location") },
        trailingIcon = {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Add note",
                    tint = ColorGirlDark
                )
            }
        }
    )
}