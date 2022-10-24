package com.example.catatan_dityast.data.repository

import kotlinx.coroutines.flow.Flow
import com.example.catatan_dityast.data.NoteDao
import com.example.catatan_dityast.model.NoteEntity


class NoteRepository(
    private val noteDao: NoteDao
) {

    fun getAllFlow(): Flow<List<NoteEntity>> = noteDao.getAllFlow()
    suspend fun insert(note: NoteEntity) = noteDao.insert(note = note)
    suspend fun update(note: NoteEntity) = noteDao.update(note = note)
    suspend fun delete(note: NoteEntity) = noteDao.delete(note = note)
}