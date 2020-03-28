package br.com.ui

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.domain.Component
import br.com.repository.component.ComponentRepository
import br.com.repository.helper.exception.BusinessException
import br.com.ui.util.Submit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject
import kotlin.coroutines.CoroutineContext

class BoxUi : KoinComponent, CoroutineScope {

    private val repository: ComponentRepository by inject()

    private lateinit var componentView: Component

    fun start(appActivity: AppCompatActivity, layoutId: Int = android.R.id.content) {
        launchWithError(appActivity) {
            componentView = repository.getComponent()
            createFragment(appActivity, layoutId, componentView.children[0])
        }
    }

    private fun createFragment(
        appActivity: AppCompatActivity,
        layoutId: Int,
        component: Component
    ) {
        appActivity.supportFragmentManager.beginTransaction()
            .replace(layoutId, ComponentFragment(component = component, submit = object : Submit {
                override fun next() {
                    val nextComponent = componentView.children.find {
                        it.path == component.navigateTo
                    }
                    if (nextComponent != null) {
                        createFragment(appActivity, layoutId, nextComponent)
                    }
                }

            })).addToBackStack(null)
            .commit()
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main
}

fun CoroutineScope.launchWithError(
    context: AppCompatActivity,
    func: suspend CoroutineScope.() -> Unit
) {
    launch {
        try {
            func()
        } catch (e: BusinessException) {
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            context.finish()
        }
    }
}