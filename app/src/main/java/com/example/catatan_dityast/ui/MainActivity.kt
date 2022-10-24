package com.example.catatan_dityast.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import com.example.catatan_dityast.ui.theme.NoteRoomTheme
import com.example.catatan_dityast.viewmodel.HomeViewModel



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val homeViewModel: HomeViewModel by viewModels()

        setContent {
            NoteRoomApp(homeViewModel = homeViewModel)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "HAllo $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteRoomTheme {
        Greeting("Android")
    }
}