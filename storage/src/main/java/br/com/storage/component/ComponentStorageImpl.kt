package br.com.storage.component

import br.com.storage.component.model.ComponentResponse
import br.com.storage.local.Json

class ComponentStorageImpl(private val json: Json): ComponentStorage {
    @Throws(Exception::class)
    override fun getComponents() = json.fromJson("component.json", ComponentResponse::class.java)?:ComponentResponse()
}