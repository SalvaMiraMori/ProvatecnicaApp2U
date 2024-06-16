package com.example.provatecnicaapp2u.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.provatecnicaapp2u.features.home.HomeScreen
import com.example.provatecnicaapp2u.features.photographerDetail.PhotographerDetailScreen

// Mètode que defineix la navegació dels objectes composables de l'aplicació
@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeScreen) {

        // Es passa navController a HomeScreen per a habilitar la navegació
        composable<HomeScreen>{
            HomeScreen(navController)
        }

        // Es passa navController a PhotographerDetailScreen per a facilitar la navegació, juntament
        // amb els nom i cognoms del fotògraf, la imatge i la descripció corresponent
        composable<PhotographerDetailScreen>{
            val args = it.toRoute<PhotographerDetailScreen>()
            PhotographerDetailScreen(navController, args.firstName, args.lastName, args.image, args.description)
        }
    }
}