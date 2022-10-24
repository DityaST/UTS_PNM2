package com.example.catatan_dityast.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import com.example.catatan_dityast.model.NoteEntity


class NoteDaoAndroidTest {

    private lateinit var sut: NoteDao
    private lateinit var mDb: AppDatabase

    @Before
    fun createDb() {
        mDb = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        sut = mDb.noteDao()
    }

    @After
    fun cleanUp() {
        mDb.close()
    }

    @Test
    fun testInsertNoteAndReadInList() = runBlocking {
        // arrange
        val fakeText = "Masukan Teks"
        val fakeNote = NoteEntity(text = fakeText)

        // act
        sut.insert(fakeNote)
        val noteList = sut.getAllFlow().first()

        // assert
        assertThat(noteList.first().text).isEqualTo(fakeText)
    }

    @Test
    fun testUpdateNoteAndReadInList() = runBlocking {
        // arrange
        val fakeText = "Masukan Teks"
        val fakeTextUpdated = "update Teks"
        val fakeNote = NoteEntity(text = fakeText)

        // act
        sut.insert(fakeNote)
        val noteList = sut.getAllFlow().first()
        sut.update(noteList.first().copy(text = fakeTextUpdated))
        val noteListUpdated = sut.getAllFlow().first()

        // assert
        assertThat(noteListUpdated.first().text).isEqualTo(fakeTextUpdated)
    }

    @Test
    fun testDeleteNoteRemovesNoteFromList() = runBlocking {
        // arrange
        val fakeText = "Masukan Teks"
        val fakeNote = NoteEntity(text = fakeText)

        // act
        sut.insert(fakeNote)
        val noteList = sut.getAllFlow().first()
        sut.delete(noteList.first())
        val noteListUpdated = sut.getAllFlow().first()

        // assert
        assertThat(noteListUpdated).isEmpty()
    }
}