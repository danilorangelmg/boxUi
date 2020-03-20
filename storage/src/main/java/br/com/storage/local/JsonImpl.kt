package br.com.storage.local

import android.content.Context
import com.squareup.moshi.Moshi

class JsonImpl(private val context: Context): Json {

    private val builder by lazy {
        Moshi.Builder().build()
    }

    override fun <T> fromJson(fileName: String, clazz: Class<T>): T? {
        return builder.adapter<T>(clazz).fromJson(getJsonFromFile(fileName))
    }

    private fun getJsonFromFile(fileName: String): String {
        val localJson = context.assets.open(fileName)
        val size = localJson.available()
        val buffer = ByteArray(size)
        localJson.read(buffer)
        localJson.close()
        val result = String(buffer)
        println(result)
        return result
    }

}