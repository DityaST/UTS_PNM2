package com.example.catatan_dityast.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catatan_dityast.ui.home.HomeScreen
import com.example.catatan_dityast.ui.note.NoteScreen
import com.example.catatan_dityast.ui.theme.NoteRoomTheme
import com.example.catatan_dityast.viewmodel.HomeViewModelAbstract



enum class Screen {
    Home, Note
}

@Composable
fun NoteRoomApp(
    homeViewModel: HomeViewModelAbstract,
) {
    val navController = rememberNavController()
    NoteRoomTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home.name,
                builder = {
                    composable(Screen.Home.name) {
                        HomeScreen(
                            homeViewModel = homeViewModel,
                            onClickNote = {
                                navController.navigate(Screen.Note.name)
                            },
                            onClickAddNote = {
                                navController.navigate(Screen.Note.name)
                            }
                        )
                    }
                    composable(Screen.Note.name) {
                        NoteScreen(
                            viewModel = homeViewModel,
                            onClickClose = {
                                navController.popBackStack()
                            },
                        )
                    }
                })
        }
    }
}