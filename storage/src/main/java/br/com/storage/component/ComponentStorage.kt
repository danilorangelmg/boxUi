package br.com.storage.component

import br.com.storage.component.model.ComponentResponse

interface ComponentStorage {
    fun getComponents(): ComponentResponse
}