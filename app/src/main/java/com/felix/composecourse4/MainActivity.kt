package com.felix.composecourse4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.felix.composecourse4.ui.theme.ComposeCourse4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCourse4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Lemonade()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Lemonade() {
    var imgResource by remember {
        mutableStateOf(1)
    }

    var squeezeCount by remember {
        mutableStateOf(0)
    }

    val image = when (imgResource) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val textDesc = when (imgResource){
        1 -> "Tap the lemon tree to select a lemon"
        2 -> "Keep tapping the lemon to squeeze it"
        3 -> "Tap the lemonade to drink it"
        else -> "Tap the empty glass to start again"
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$textDesc")

        Spacer(modifier = Modifier.padding(vertical = 8.dp))
        
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .clickable {
                    when (imgResource) {
                        1 -> {
                            imgResource = 2
                            squeezeCount = (2..4).random()
                        }
                        2 -> {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                imgResource = 3
                            }
                        }
                        3 -> {
                            imgResource = 4
                        }
                        else -> {
                            imgResource = 1
                        }
                    }
                })
    }
}