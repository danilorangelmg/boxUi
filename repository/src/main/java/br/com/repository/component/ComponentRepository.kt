package br.com.repository.component

import br.com.domain.Component
import br.com.repository.helper.exception.BusinessException

interface ComponentRepository {

    @Throws(BusinessException::class)
    suspend fun getComponent(): Component
}