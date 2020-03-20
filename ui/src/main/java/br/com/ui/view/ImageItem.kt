package br.com.ui.view

import android.widget.FrameLayout
import br.com.domain.Component
import br.com.domain.Gravity
import br.com.ui.R
import br.com.utils.loadImageUrl
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.image_layout.view.*

class ImageItem(private val component: Component) : Item() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.imageview.apply {
            loadImageUrl(component.url)
            this.layoutParams = FrameLayout.LayoutParams(layoutParams.width, layoutParams.height).apply {
                gravity = when (component.gravity) {
                    Gravity.NONE, Gravity.LEFT -> android.view.Gravity.START
                    Gravity.CENTER -> android.view.Gravity.CENTER
                    Gravity.RIGHT -> android.view.Gravity.END
                    else -> 0
                }
            }
        }
    }

    override fun getLayout(): Int = R.layout.image_layout

}