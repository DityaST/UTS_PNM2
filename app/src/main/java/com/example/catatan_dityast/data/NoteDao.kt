package com.example.catatan_dityast.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.catatan_dityast.model.NoteEntity

@Dao
interface NoteDao {

    @Query("select * from note")
    fun getAllFlow(): Flow<List<NoteEntity>>

    @Insert
    suspend fun insert(note: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)

    @Delete
    suspend fun delete(note: NoteEntity)
}