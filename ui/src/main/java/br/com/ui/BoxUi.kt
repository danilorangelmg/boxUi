package br.com.ui

import androidx.appcompat.app.AppCompatActivity
import br.com.domain.Component
import br.com.repository.component.ComponentRepository
import br.com.ui.util.Submit
import kotlinx.coroutines.*
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.coroutines.CoroutineContext

class BoxUi : KoinComponent, CoroutineScope {

    private val repository: ComponentRepository by inject()

    private lateinit var componentView: Component

    fun start(appActivity: AppCompatActivity, layoutId: Int = android.R.id.content) {
        launch {
            componentView = repository.getComponent()
            createFragment(appActivity, layoutId, componentView.children[0])
        }
    }

    private fun createFragment(appActivity: AppCompatActivity, layoutId: Int, component: Component) {
        appActivity.supportFragmentManager.beginTransaction()
            .replace(layoutId, ComponentFragment(component = component, submit = object: Submit {
                override fun next() {
                    val nextComponent = componentView.children.find {
                        it.path == component.navigateTo
                    }
                    if (nextComponent != null) {
                        createFragment(appActivity, layoutId, nextComponent)
                    }
                }

            })).addToBackStack(null)
            .commitAllowingStateLoss()
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main
}