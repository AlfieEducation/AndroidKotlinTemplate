package com.template.project

import android.app.Application
import timber.log.Timber

class TemplateApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}