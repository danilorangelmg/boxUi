package br.com.ui

import br.com.domain.Type
import br.com.ui.view.ButtonItem
import br.com.ui.view.ImageItem
import br.com.ui.view.InputItem
import br.com.ui.view.LabelItem
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlin.reflect.KClass

sealed class ComponentUi<T: Item>(val id: Type, val androidComponentClass: KClass<T>) {
    object INPUT: ComponentUi<InputItem>(id = Type.INPUT, androidComponentClass = InputItem::class)
    object LABEL: ComponentUi<LabelItem>(id = Type.LABEL, androidComponentClass = LabelItem::class)
    object BUTTON: ComponentUi<ButtonItem>(id = Type.BUTTON, androidComponentClass = ButtonItem::class)
    object BUTTON_SUBMIT: ComponentUi<ButtonItem>(id = Type.BUTTON_SUBMIT, androidComponentClass = ButtonItem::class)
    object IMAGE: ComponentUi<ImageItem>(id = Type.IMAGE, androidComponentClass = ImageItem::class)
}
