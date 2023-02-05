package kz.tinkoff.kinopoisk.data.di

import android.content.Context
import androidx.room.Room
import kz.tinkoff.kinopoisk.BuildConfig
import kz.tinkoff.kinopoisk.data.api.FilmApi
import kz.tinkoff.kinopoisk.data.db.DB_NAME
import kz.tinkoff.kinopoisk.data.db.KinopoiskDao
import kz.tinkoff.kinopoisk.data.db.KinopoiskDatabase
import kz.tinkoff.kinopoisk.data.repo.FilmLocalRepositoryImpl
import kz.tinkoff.kinopoisk.data.repo.FilmRepositoryImpl
import kz.tinkoff.kinopoisk.domain.repo.FilmLocalRepository
import kz.tinkoff.kinopoisk.domain.repo.FilmRepository
import kz.tinkoff.kinopoisk.presentation.details.FilmDetailViewModel
import kz.tinkoff.kinopoisk.presentation.favourites.FavouriteViewModel
import kz.tinkoff.kinopoisk.presentation.top.TopFilmViewModel
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val API_KEY = "X-API-KEY"

val appModule = module {
    factory<OkHttpClient> { provideClient() }
    single<GsonConverterFactory> { provideGson() }
    single<Retrofit> { provideRetrofit(get(), get()) }

    single<KinopoiskDatabase> { provideKinopoiskDb(androidApplication()) }
    single<KinopoiskDao> { provideKinopoiskDao(get()) }

    single<FilmApi> { get<Retrofit>().create(FilmApi::class.java) }
    single<FilmRepository> { FilmRepositoryImpl(api = get()) }
    single<FilmLocalRepository> { FilmLocalRepositoryImpl(get()) }

    viewModel { TopFilmViewModel(get(), get()) }
    viewModel { FavouriteViewModel(get()) }
    viewModel { FilmDetailViewModel(get()) }

}

fun provideGson(): GsonConverterFactory = GsonConverterFactory.create()


private fun provideClient(): OkHttpClient {
    val httpClient = OkHttpClient.Builder()
    httpClient.networkInterceptors().add(
        Interceptor { chain ->
            val original = chain.request()

            val requestBuilder = original.newBuilder()
            requestBuilder.header(API_KEY, BuildConfig.XAPIKEY)
                .method(original.method(), original.body())
                .build()

            chain.proceed(requestBuilder.build())

        }
    )
    return httpClient.build()
}


private fun provideRetrofit(gson: GsonConverterFactory, httpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(httpClient)
        .addConverterFactory(gson)
        .build()
}

private fun provideKinopoiskDb(applicationContext: Context): KinopoiskDatabase {
    return Room.databaseBuilder(applicationContext, KinopoiskDatabase::class.java, DB_NAME).build()
}

private fun provideKinopoiskDao(database: KinopoiskDatabase): KinopoiskDao {
    return database.filmsDao()
}