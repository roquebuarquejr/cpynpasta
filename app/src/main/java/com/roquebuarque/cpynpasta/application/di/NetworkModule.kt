package com.roquebuarque.cpynpasta.application.di

import com.google.gson.Gson
import com.roquebuarque.cpynpasta.BuildConfig
import com.roquebuarque.cpynpasta.model.RecipeService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule() {

    @Provides
    @ApplicationScope
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
    }

    @Provides
    @ApplicationScope
    fun provideOkHttpClient(log : HttpLoggingInterceptor): OkHttpClient {
       return OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url
                val newUrl = originalUrl.newBuilder()
                    .addQueryParameter("apiKey", BuildConfig.API_KEY)
                    .build()

                val newRequest = originalRequest.newBuilder().url(newUrl).build()

                chain.proceed(
                    newRequest
                        .newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .build()
                )
            }
            .addInterceptor(log)
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideRecipeService(retrofit: Retrofit) = createNetworkService<RecipeService>(retrofit)

    private inline fun <reified T> createNetworkService(retrofit: Retrofit): T{
        return retrofit.create(T::class.java)
    }


}