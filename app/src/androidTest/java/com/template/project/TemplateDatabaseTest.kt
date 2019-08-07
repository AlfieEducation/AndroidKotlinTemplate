package com.template.project

import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.template.project.database.TemplateDatabase
import com.template.project.database.TemplateDatabaseDao
import com.template.project.database.TemplateEntity
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TemplateDatabaseTest {

    private lateinit var templateDao: TemplateDatabaseDao
    private lateinit var db: TemplateDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        db = Room.inMemoryDatabaseBuilder(context, TemplateDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        templateDao = db.tempDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetEntity() {
        val templateEntity = TemplateEntity()
        templateDao.insert(templateEntity)
        val temp = templateDao.getLastEntity()
        assertEquals(temp?.intField, -1)
    }
}

