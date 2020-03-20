package br.com.ui

import androidx.appcompat.app.AppCompatActivity
import br.com.repository.component.ComponentRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class BoxUi: KoinComponent {

    private val repository: ComponentRepository by inject()

    fun start(appActivity: AppCompatActivity, layoutId: Int = android.R.id.content) {
        GlobalScope.launch {
            appActivity.supportFragmentManager.beginTransaction()
                .replace(layoutId, ComponentFragment(repository.getComponent())).addToBackStack(null)
                .commitAllowingStateLoss()
        }
    }
}