package com.example.catatan_dityast.ui.note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.catatan_dityast.*
import com.example.catatan_dityast.R
import com.example.catatan_dityast.model.NoteEntity
import com.example.catatan_dityast.viewmodel.HomeViewModelAbstract

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    viewModel: HomeViewModelAbstract,
    onClickClose: () -> Unit,
) {
    val note = viewModel.selectedNoteState.value
    val txtState = rememberSaveable { mutableStateOf(note?.text ?: "") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(modifier = Modifier.testTag(NOTE_TITLE), text = "edit") },
                actions = {
                    IconButton(
                        modifier = Modifier.testTag(NOTE_BUTTON_DONE),
                        onClick = {
                            note?.let {
                                viewModel.addOrUpdateNote(it.copy(text = txtState.value))
                            } ?: run {
                                viewModel.addOrUpdateNote(NoteEntity(text = txtState.value))
                            }
                            onClickClose()
                        }) {
                        Icon(
                            imageVector = Icons.Rounded.Done,
                            contentDescription = stringResource(
                                id = R.string.screen_home_popup_button_save
                            )
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.testTag(NOTE_BUTTON_BACK),
                        onClick = onClickClose
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.screen_home_popup_button_dismiss
                            )
                        )
                    }
                }
            )
        }
    ) { contentPadding ->

        Column(
            modifier = Modifier
                .padding(contentPadding)
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            BasicTextField(
                modifier = Modifier
                    .testTag(NOTE_TEXT_FIELD)
                    .padding(start = 16.dp, end = 16.dp, top = 24.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                value = txtState.value,
                onValueChange = { txt: String ->
                    txtState.value = txt
                },
            )
        }
    }
}