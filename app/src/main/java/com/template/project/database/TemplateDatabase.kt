package com.template.project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TemplateEntity::class], version = 1, exportSchema = false)
abstract class TemplateDatabase : RoomDatabase() {

    abstract val tempDatabaseDao: TemplateDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: TemplateDatabase? = null

       fun getInstance(context: Context): TemplateDatabase {

           synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TemplateDatabase::class.java,
                        "template_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}