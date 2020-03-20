package br.com.storage.local

interface Json {
    fun <T> fromJson(fileName: String, clazz: Class<T>) : T?
}