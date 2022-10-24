package com.example.catatan_dityast.model

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) var roomId: Long? = null,
    var text: String,
)