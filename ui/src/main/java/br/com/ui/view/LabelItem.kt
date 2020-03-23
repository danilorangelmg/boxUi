package br.com.ui.view

import br.com.domain.Component
import br.com.domain.Gravity
import br.com.ui.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.label_layout.view.*

class LabelItem(private val component: Component) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.textview.apply{
            text = component.value.toString()
            gravity = when(component.gravity) {
                Gravity.NONE, Gravity.RIGHT -> android.view.Gravity.RIGHT
                Gravity.CENTER -> android.view.Gravity.CENTER_HORIZONTAL
                Gravity.LEFT -> android.view.Gravity.LEFT
                else -> 0
            }
        }
    }

    override fun getLayout(): Int = R.layout.label_layout

}