package com.example.provatecnicaapp2u.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.provatecnicaapp2u.localdatabase.RealmDatabase
import com.example.provatecnicaapp2u.model.Photographer
import com.example.provatecnicaapp2u.util.services.InphototestService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

object HomeViewModel : ViewModel(){
    private val TAG = "HomeViewModel"
    private val _photographers = MutableStateFlow<List<Photographer>>(emptyList())

    val photographers: StateFlow<List<Photographer>> get() = _photographers

    init{
        if(_photographers.value.isEmpty()){
            getPhotographersFromRealmDatabase()
            getPhotographers()
        }
    }

    // Obté les dades dels fotògrafs de l'API i les guarda a la base de dades local
    fun getPhotographers(){
        viewModelScope.launch {
            try{
                val result = InphototestService().getPhotographers()
                if(result == null){
                    Log.d(TAG, "The response from the API was null")
                }else{
                    _photographers.value = result.results
                    RealmDatabase.writeListOfPhotographers(result.results)
                    Log.d(TAG, "Photographers fetched succesfully")
                }
            }catch(e: Exception){
                Log.e(TAG, "Error fetching phtotographers", e)
            }
        }
    }

    fun getPhotographersFromRealmDatabase(){
        viewModelScope.launch{
            try{
                val result = RealmDatabase.readAllPhotographers()
                _photographers.value = result
                Log.d(TAG, "Photographers fetched succesfully from local database")
            }catch(e: Exception){
                Log.e(TAG, "Error fetching phtotographers", e)
            }

        }
    }
}