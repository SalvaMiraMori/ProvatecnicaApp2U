package com.example.provatecnicaapp2u.util.services

import android.util.Log
import com.example.provatecnicaapp2u.dl.RetrofitHelper
import com.example.provatecnicaapp2u.model.GetPhotographersResponse
import com.example.provatecnicaapp2u.network.InphototestApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class InphototestService {
    private val retrofit = RetrofitHelper.getRetrofit()
    private val TAG = "InphototestService"

    // Mètode que retorna les dades obtingudes mitjançant el mètode GET de l'API proporcionada
    suspend fun getPhotographers(): GetPhotographersResponse?{
        return withContext(Dispatchers.IO){
            val response = retrofit.create(InphototestApiClient::class.java).getPhotographers()
            response.body()
        }
    }
}