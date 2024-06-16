package com.example.provatecnicaapp2u.dl

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    val username = "test@gmail.com"
    val password = "1234"
    val client = OkHttpClient.Builder().addInterceptor(BasicAuthInterceptor(username, password))
        // .connectTimeout(60, TimeUnit.SECONDS) // Augmenta el temps de connexió a 60 segons
        // .writeTimeout(60, TimeUnit.SECONDS) // Augmenta el temps d'escriptura a 60 segons
        // .readTimeout(60, TimeUnit.SECONDS) // Augmenta el temps de lectura a 60 segons
        .build()

    fun getRetrofit(): Retrofit {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setLenient()
        val gson = gsonBuilder.create()

        return Retrofit.Builder().client(client)
            .baseUrl("https://inphototest.app2u.es/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}