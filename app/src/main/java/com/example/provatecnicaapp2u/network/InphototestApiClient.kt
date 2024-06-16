package com.example.provatecnicaapp2u.network

import com.example.provatecnicaapp2u.model.GetPhotographersResponse
import retrofit2.Response
import retrofit2.http.GET

// Interfície que defineix les crides utilitzables a la API proporcionada per Retrofit
interface InphototestApiClient {
    @GET("photographer")
    suspend fun getPhotographers(): Response<GetPhotographersResponse>
}