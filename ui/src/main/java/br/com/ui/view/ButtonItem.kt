package br.com.ui.view

import br.com.domain.Component
import br.com.ui.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.button_layout.view.*
import java.lang.RuntimeException

class ButtonItem(private val component: Component) : Item() {

    private val submitValidationList: ArrayList<ButtonSubmitValidation> = ArrayList()
    private var submit: ButtonSubmit? = null

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.containerView.button.apply {
            text = component.value
            setOnClickListener {
                try {
                    for (element in submitValidationList) {
                        if (!element.validate()) {
                            throw RuntimeException()
                        }
                    }
                    submit?.next()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun getLayout(): Int = R.layout.button_layout

    fun register(submitValidation: ButtonSubmitValidation) {
        submitValidationList.add(submitValidation)
    }

    fun submit(submit: ButtonSubmit) {
        this.submit = submit
    }

    interface ButtonSubmitValidation {
        fun validate(): Boolean
    }

    interface ButtonSubmit {
        fun next(): Boolean
    }
}