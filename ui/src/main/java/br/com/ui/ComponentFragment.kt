package br.com.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.domain.Component
import br.com.domain.Type
import br.com.ui.model.ComponentUi
import br.com.ui.util.FooterLinearLayoutManager
import br.com.ui.util.Submit
import br.com.ui.view.ButtonItem
import br.com.ui.view.InputItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.component_fragment.*
import java.util.*
import kotlin.reflect.KClass

internal class ComponentFragment(private val component: Component, val submit: Submit) : Fragment() {

    private lateinit var groupAdapter: CustomGroupAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.component_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        groupAdapter = createAdapter()

        recyclerView.apply {
            layoutManager = if (component.children.any { it.type == Type.BUTTON_SUBMIT }) {
                FooterLinearLayoutManager(context)
            } else {
                GridLayoutManager(context, groupAdapter.spanCount).apply {
                    spanSizeLookup = groupAdapter.spanSizeLookup
                }
            }
            adapter = groupAdapter
        }

    }

    private fun createAdapter(): CustomGroupAdapter {
        val groupAdapter = CustomGroupAdapter().apply {
            spanCount = 12
        }

        val items = ComponentUi::class.sealedSubclasses.map { it.objectInstance }

        component.children.forEach {
            val item = items.filter { filterItem ->
                filterItem?.id == it.type
            }
            if (item.isNotEmpty()) {
                item[0]?.androidComponentClass?.constructors?.first()?.call(it)?.let { comp ->
                    groupAdapter.add(comp)
                }
            }
        }

        groupAdapter.getItemsByType(ButtonItem::class.java).takeIf { it.isNotEmpty() }?.run {
            (this[0] as ButtonItem).apply {
                register(groupAdapter.getItemsByType(InputItem::class.java).map { it as InputItem })
                submit(submit)
            }
        }
        return groupAdapter
    }
}

internal class CustomGroupAdapter : GroupAdapter<GroupieViewHolder>() {
    private val items = LinkedList<Item>()

    fun add(item: Item) {
        items.add(item)
        super.add(item)
    }

    fun getItemsByType(clazz: Class<*>) =
        items.map { it }.filter { it.javaClass == clazz }
}