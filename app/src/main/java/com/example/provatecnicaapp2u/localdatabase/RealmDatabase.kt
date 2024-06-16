package com.example.provatecnicaapp2u.localdatabase

import android.util.Log
import com.example.provatecnicaapp2u.localdatabase.model.RealmPhotographer
import com.example.provatecnicaapp2u.model.Photographer
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import io.realm.kotlin.query.RealmQuery
import io.realm.kotlin.query.RealmResults
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.currentCoroutineContext

// Objecte que permet controlar les escriptures i lectures de dades a la base de dades local
object RealmDatabase {

    private var configuration = RealmConfiguration.Builder(schema = setOf(RealmPhotographer::class)).deleteRealmIfMigrationNeeded().build()
    private var realm = Realm.open(configuration)
    private const val TAG = "RealmDatabase"

    // Escriu un fotògraf a la base de dades local
    suspend fun writePhotographer(photographer: Photographer){
        try{
            val realmPhotographer = RealmPhotographer().apply {
                id = photographer.id
                guid = photographer.guid
                email = photographer.email
                first_name = photographer.first_name
                last_name = photographer.last_name
                is_removed = photographer.is_removed
                description = photographer.description
                avatar = photographer.avatar
                image = photographer.image
                facebook = photographer.facebook
                instagram = photographer.instagram
                webpage = photographer.webpage
            }

            realm.write{
                copyToRealm(realmPhotographer)
                Log.d(TAG, "Wrote photographer with guid " + photographer.guid + " to local database")
            }

        }catch(e: Exception){
            Log.e(TAG, "Error writing phtotographer with guid " + photographer.guid + " to local database", e)
        }

    }

    // Escriu la llista de fotògrafs passada a la base de dades locals.
    // Comprova si el fotògraf ja existeix a la base de dades abans d'afegir-lo.
    // Crea una corrutina per a cada escriptura per a paralitzar el mínim possible el fil principal
    // de l'aplicació.
    suspend fun writeListOfPhotographers(photographerList: List<Photographer>){
        try{
            photographerList.forEach { photographer ->
                CoroutineScope(currentCoroutineContext()).async{
                    val photographerByGuidQuery: RealmQuery<RealmPhotographer> = realm.query<RealmPhotographer>("guid = $0", photographer.guid)
                    val filteredByGuid: RealmResults<RealmPhotographer> = photographerByGuidQuery.find()
                    if(filteredByGuid.size == 0){
                        writePhotographer(photographer)
                    }
                }
            }
        }catch(e: Exception){
            Log.e(TAG, "Error writing phtotographers to local database", e)
        }

    }

    // Llegeix tots els fotògrafs guardats a la base de dades local.
    fun readAllPhotographers(): List<Photographer>{
        val realmPhotographers = realm.query<RealmPhotographer>().find()
        val photographerList = mutableListOf<Photographer>()
        realmPhotographers.forEach { realmPhotographer ->
            photographerList.add(Photographer(
                realmPhotographer.id,
                realmPhotographer.guid,
                realmPhotographer.email,
                realmPhotographer.first_name,
                realmPhotographer.last_name,
                realmPhotographer.is_removed,
                realmPhotographer.description,
                realmPhotographer.avatar,
                realmPhotographer.image,
                realmPhotographer.facebook,
                realmPhotographer.instagram,
                realmPhotographer.webpage
            ))
        }

        Log.d(TAG, "Fetched " + photographerList.size.toString() + " photographers from RealmDatabase")

        return photographerList
    }
}