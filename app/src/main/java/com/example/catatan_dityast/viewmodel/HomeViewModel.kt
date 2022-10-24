package com.example.catatan_dityast.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.example.catatan_dityast.data.repository.NoteRepository
import com.example.catatan_dityast.model.NoteEntity
import javax.inject.Inject



interface HomeViewModelAbstract {
    val selectedNoteState: State<NoteEntity?>
    val noteListFlow: Flow<List<NoteEntity>>
    fun addOrUpdateNote(note: NoteEntity)
    fun deleteNote(note: NoteEntity)
    fun selectNote(note: NoteEntity)
    fun resetSelectedNote()
}

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val noteRepository: NoteRepository,
): ViewModel(), HomeViewModelAbstract {
    private val ioScope = CoroutineScope(Dispatchers.IO)

    private val _selectedNoteState: MutableState<NoteEntity?> = mutableStateOf(null)
    override val selectedNoteState: State<NoteEntity?>
        get() = _selectedNoteState

    override val noteListFlow: Flow<List<NoteEntity>> = noteRepository.getAllFlow()

    override fun addOrUpdateNote(note: NoteEntity) {
        ioScope.launch {
            if (note.roomId == null) {
                noteRepository.insert(note = note)
            } else {
                noteRepository.update(note = note)
            }
        }
    }

    override fun deleteNote(note: NoteEntity) {
        ioScope.launch {
            noteRepository.delete(note = note)
        }
    }

    override fun selectNote(note: NoteEntity) {
        _selectedNoteState.value = note
    }

    override fun resetSelectedNote() {
        _selectedNoteState.value = null
    }

}