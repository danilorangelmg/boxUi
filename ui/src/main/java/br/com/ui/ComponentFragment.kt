package br.com.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.domain.Component
import br.com.domain.Type
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.component_fragment.*

class ComponentFragment(private val component: Component) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.component_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            spanCount = 12
        }

        val items = ComponentUi::class.sealedSubclasses.map { it.objectInstance }

        component.children.forEach {
            val item = items.filter { filterItem ->
                filterItem?.id == it.type
            }
            if (item.isNotEmpty()) {
                val item = item[0]?.androidComponentClass?.constructors?.first()?.call(it)
                item?.let { it1 -> groupAdapter.add(it1) }
            }

        }

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

}