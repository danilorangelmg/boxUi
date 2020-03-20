package br.com.ui.util

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FooterLinearLayoutManager(context: Context) : LinearLayoutManager(context) {

    private var parentBottom: Int = 0
    private var offset: Int = 0

    override fun layoutDecoratedWithMargins(child: View, left: Int, top: Int, right: Int, bottom: Int) {
        val lp = child.layoutParams as RecyclerView.LayoutParams
        if (lp.viewAdapterPosition < itemCount - 1)
            return super.layoutDecoratedWithMargins(child, left, top, right, bottom)

        if (parentBottom == 0) {
            parentBottom = height - paddingBottom
        }

        return if (bottom < parentBottom) {
            if (offset == 0) {
                offset = parentBottom - bottom
            }
            super.layoutDecoratedWithMargins(child, left, top + offset, right, bottom + offset)
        } else {
            super.layoutDecoratedWithMargins(child, left, top, right, bottom)
        }
    }
}