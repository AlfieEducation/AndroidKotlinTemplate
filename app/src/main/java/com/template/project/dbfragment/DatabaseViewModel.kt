package com.template.project.dbfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.template.project.database.TemplateDatabaseDao
import com.template.project.database.TemplateEntity
import com.template.project.formatEntities
import kotlinx.coroutines.*

class DatabaseViewModel(
    val database: TemplateDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var curEntity = MutableLiveData<TemplateEntity?>()

    val entities = database.getAllEntities()

    val entityString = Transformations.map(entities) { entities ->
        formatEntities(entities, application.resources)
    }

    init {
        initializeEntityt()
    }

    private fun initializeEntityt() {
        uiScope.launch {
            curEntity.value = getEntityFromDatabase()
        }
    }

    private suspend fun insert(tempEntity: TemplateEntity) {
        withContext(Dispatchers.IO) {
            database.insert(tempEntity)
        }
    }

    private suspend fun getEntityFromDatabase(): TemplateEntity? {
        return withContext(Dispatchers.IO) {
            var tempEntity = database.getLastEntity()
            tempEntity
        }
    }

    fun onStartTracking() {
        uiScope.launch {
            // Create a new night, which captures the current time,
            // and insert it into the database.
            val newEntity = TemplateEntity()

            insert(newEntity)

            curEntity.value = getEntityFromDatabase()
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            curEntity.value = null
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }

}