package br.com.repository.di

import br.com.repository.component.ComponentRepository
import br.com.repository.component.ComponentRepositoryImpl
import br.com.storage.component.ComponentStorage
import br.com.storage.component.ComponentStorageImpl
import br.com.storage.local.Json
import br.com.storage.local.JsonImpl
import org.koin.dsl.module

val repositoryModules by lazy {
    listOf(component, localStorage)
}

val component = module {
    single<ComponentRepository> { ComponentRepositoryImpl(storage = get()) }
    factory<ComponentStorage> { ComponentStorageImpl(json = get())}
}

val localStorage = module {
    single<Json> { JsonImpl(context = get()) }
}


