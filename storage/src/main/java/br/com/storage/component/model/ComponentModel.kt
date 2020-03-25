package br.com.storage.component.model

data class ComponentResponse(
    val type: Type = Type.NONE,
    val orientation: Orientation = Orientation.NONE,
    val validationType: ValidationType = ValidationType.NONE,
    val value: String? = null,
    val label: String? = null,
    val gravity: Gravity = Gravity.NONE,
    val errorMessage: String? = null,
    val inputType: InputType = InputType.NONE,
    val path: String? = null,
    val navigateTo: String? = null,
    val title: String? = null,
    val url: String? = null,
    val children: List<ComponentResponse> = listOf()
)

enum class Type {
    NONE,
    INPUT,
    LABEL,
    BUTTON,
    BUTTON_SUBMIT,
    IMAGE,
    CONTAINER,
    NAV_CONTAINER
}

enum class Orientation {
    NONE,
    VERTICAL,
    HORIZONTAL
}

enum class ValidationType {
    NONE,
    MINOR_AGE,
    VALUE_MIN,
    VALUE_MAX,
    MORE_THAN,
    LESS_THAN,
    NOT_EMPTY
}

enum class Gravity {
    NONE,
    CENTER,
    RIGHT,
    LEFT
}

enum class InputType {
    NONE,
    NUMBER,
    NORMAL //todo verify
}