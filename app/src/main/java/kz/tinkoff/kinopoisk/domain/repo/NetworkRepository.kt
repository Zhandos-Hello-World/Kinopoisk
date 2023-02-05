package kz.tinkoff.kinopoisk.domain.repo

import com.google.gson.Gson
import org.koin.java.KoinJavaComponent
import retrofit2.Response

//NetworkRepository
abstract class NetworkRepository {
    private val gson by KoinJavaComponent.inject<Gson>(Gson::class.java)

    suspend fun <T> request(block: suspend () -> Response<T>): T {
        val response: Response<T> = block()
        if (response.isSuccessful) return response.body()!!

        val errorBody = response.errorBody()?.string()
        if (response.code() >= 500) throw Exception()

        val tempException = gson.fromJson(errorBody, Exception::class.java)

        throw Exception(tempException)
    }
}