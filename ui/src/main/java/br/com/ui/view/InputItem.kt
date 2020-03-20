package br.com.ui.view

import android.view.View
import br.com.domain.Component
import br.com.domain.InputType
import br.com.domain.ValidationType
import br.com.ui.R
import com.google.android.material.textfield.TextInputLayout
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.input_layout.view.*

class InputItem(private val component: Component) : Item(), ButtonItem.ButtonSubmitValidation {

    private var rootView: View? = null

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        rootView = viewHolder.containerView
        viewHolder.containerView.editText.apply {
            hint = component.label
            setText(component.value)
            inputType = when(component.inputType) {
                InputType.NONE, InputType.NORMAL -> android.text.InputType.TYPE_CLASS_TEXT
                InputType.NUMBER -> android.text.InputType.TYPE_CLASS_NUMBER
            }
        }
    }

    override fun getLayout(): Int = R.layout.input_layout

    override fun validate(): Boolean {
        val textInputLayout = (rootView?.textInputLayout as TextInputLayout)
        val value = rootView?.editText?.text.toString()

        textInputLayout.error = null

        return when (component.validationType) {
            ValidationType.NONE -> true
            ValidationType.MINOR_AGE -> {
                if (emptyValidation(value, textInputLayout)) return false
                val isValid = value.toInt() > 18
                if (isValid.not()) {
                    textInputLayout.error = component.errorMessage
                }
                isValid
            }
            ValidationType.VALUE_MIN -> true //todo
            ValidationType.VALUE_MAX -> true //todo
            ValidationType.MORE_THAN -> true //todo
            ValidationType.LESS_THAN -> true //todo
            ValidationType.NOT_EMPTY -> emptyValidation(value, textInputLayout)
        }
    }

    private fun emptyValidation(
        value: String,
        textInputLayout: TextInputLayout
    ): Boolean {
        if (value.isEmpty()) {
            textInputLayout.error = component.errorMessage
            return true
        }
        return false
    }

}