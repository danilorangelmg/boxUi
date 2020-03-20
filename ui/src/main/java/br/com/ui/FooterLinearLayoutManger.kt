package br.com.ui

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FooterLinearLayoutManager(context: Context) : LinearLayoutManager(context) {

    override fun layoutDecoratedWithMargins(child: View, left: Int, top: Int, right: Int, bottom: Int) {
        val lp = child.layoutParams as RecyclerView.LayoutParams
        if (lp.viewAdapterPosition < itemCount - 1)
            return super.layoutDecoratedWithMargins(child, left, top, right, bottom)

        val parentBottom = height - paddingBottom
        return if (bottom < parentBottom) {
            val offset = parentBottom - bottom
            super.layoutDecoratedWithMargins(child, left, top + offset, right, bottom + offset)
        } else {
            super.layoutDecoratedWithMargins(child, left, top, right, bottom)
        }
    }
}