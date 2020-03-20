package br.com.network.api

import br.com.network.config.ServiceConfiguration

class ApiServiceImpl : ApiService {

    private val ERROR_MESSAGE = "Erro ao buscar dados, Tente novamente em alguns instantes!"

    private val soccerApi by lazy {
        ServiceConfiguration().configureApiInterface(Api::class.java)
    }

//    @Throws(NetworkException::class)
//    override suspend fun get(): Model {
//         try {
//             return withContext(Dispatchers.IO) {
//                soccerApi.getScore()
//            }
//        } catch (e: HttpException) {
//            throw NetworkException(ERROR_MESSAGE)
//        }
//    }
}