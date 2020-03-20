package br.com.repository.component

import br.com.domain.*
import br.com.repository.helper.exception.BusinessException
import br.com.storage.component.ComponentStorage
import br.com.storage.component.model.ComponentResponse
import br.com.utils.toDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ComponentRepositoryImpl(val storage: ComponentStorage) : ComponentRepository {

    @Throws(Exception::class)
    override suspend fun getComponent(): Component {
        try {
            return withContext(Dispatchers.IO) {
                storage.getComponents().toDomain {
                    parseComponent(it)
                }
            }
        } catch (e: Exception) {
            throw BusinessException(e.message)
        }
    }

    private fun parseComponent(componentResponse: ComponentResponse): Component {
        return Component(
            type = Type.valueOf(componentResponse.type.name),
            orientation = Orientation.valueOf(componentResponse.orientation.name),
            validationType = ValidationType.valueOf(componentResponse.validationType.name),
            value = componentResponse.value,
            label = componentResponse.label,
            gravity = Gravity.valueOf(componentResponse.gravity.name),
            errorMessage = componentResponse.errorMessage,
            url = componentResponse.url,
            children = componentResponse.children.map { child ->
                parseComponent(child)
            }
        )
    }
}