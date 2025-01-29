package com.example.nbapp.di

import com.example.nbapp.BuildConfig
import com.example.nbapp.data.remote.NBAppApi
import com.example.nbapp.repository.PlayerListRepository
import com.example.nbapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    @Singleton
    @Provides
    fun provideNBAppRepository(
        api: NBAppApi
    ) = PlayerListRepository(api)

    /**
     * Adding the authorization token here.
     * Since it's static and used on all of the requests, there is no need for variable.
     */
    private fun okHttpClient() = OkHttpClient().newBuilder()
        .addInterceptor(
            Interceptor { chain ->
                val request: Request = chain.request()
                    .newBuilder()
                    .header("Authorization", BuildConfig.API_KEY)
                    .build()
                chain.proceed(request)
            }
        )

    /**
     * Provides NBAppApi for whole app.
     *
     * @return Api Interface with already set up converter, Base URL and authentication.
     */
    @Singleton
    @Provides
    fun provideNBAppApi(): NBAppApi {
        return Retrofit.Builder()
            .addConverterFactory(
                json.asConverterFactory(
                    "application/json; charset=UTF8".toMediaType()
                )
            )
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient().build())
            .build().create(NBAppApi::class.java)
    }
}