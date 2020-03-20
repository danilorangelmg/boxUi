package br.com.ui

import android.app.Application
import br.com.repository.di.repositoryModules
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext

open class BoxUiApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BoxUiApplication)
            modules(repositoryModules)
        }
    }

}