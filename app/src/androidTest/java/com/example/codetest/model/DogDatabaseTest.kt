package com.example.codetest.model

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class DogDatabaseTest : TestCase(){

    private lateinit var db: DogDatabase
    private lateinit var dao:DogDao

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, DogDatabase::class.java).build()
        dao = db.dogDao()

    }

    @Test
    fun saveDogBreed_retrievesDogBreed() = runBlocking{
        // GIVEN - A new DogBreed saved in the database.
        val dogBreed = DogBreed("123","Breed", "10", "groups", "Fun", "Loving","https://www.google.com/")
        dao.insertAll(dogBreed)

        // WHEN  - DogBreed retrieved.
        val dogs = dao.getAllDogs()

        // THEN - DogBreed is returned.
        assertThat(dogs.contains(dogBreed), `is`(true))
    }

    @After
    fun closeDb(){
        db.close()

    }

}