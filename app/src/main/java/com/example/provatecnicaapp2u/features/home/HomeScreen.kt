package com.example.provatecnicaapp2u.features.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.provatecnicaapp2u.R
import com.example.provatecnicaapp2u.ui.theme.ProvaTecnicaApp2UTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.provatecnicaapp2u.features.photographerDetail.PhotographerDetailScreen
import com.example.provatecnicaapp2u.model.Photographer
import com.example.provatecnicaapp2u.ui.theme.darkMustardColor
import com.example.provatecnicaapp2u.ui.theme.salmonColor
import kotlinx.serialization.Serializable

// Objecte utilitzat per a la navegació entre objectes composables
@Serializable
object HomeScreen

// Objecte composable responsable de mostrar la llista de fotògrafs
@Composable
fun HomeScreen(navController: NavController){
    val TAG = "HomeScreen"

    val homeViewModel = HomeViewModel

    val photographers by HomeViewModel.photographers.collectAsState()

    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

    // Controla l'estat de la pantalla Home
    LaunchedEffect(lifecycleState) {
        when (lifecycleState) {
            Lifecycle.State.DESTROYED -> {
                Log.d(TAG,  "DESTROYED")
            }
            Lifecycle.State.INITIALIZED -> {
                Log.d(TAG,  "INITIALIZED")
            }
            Lifecycle.State.CREATED -> {
                Log.d(TAG,  "CREATED")
            }
            Lifecycle.State.STARTED -> {
                Log.d(TAG,  "STARTED")
            }

            // Cada cop que la pantalla Home és represa, s'actualitza la llista de fotògrafs
            Lifecycle.State.RESUMED -> {
                homeViewModel.getPhotographers()
                Log.d(TAG,  "RESUMED")
            }
        }
    }

    // Element que controla l'estructura principal de la pantalla Home
    Scaffold (
        topBar = {
            Column{
                Column(modifier = Modifier.background(salmonColor)){
                    Row(Modifier.padding(16.dp)){
                        TopAppBarElement(icon = R.drawable.ic_camera, text = "Scan", salmonColor)
                        Spacer(Modifier.weight(1f))
                        IconButton(onClick = {  }, modifier = Modifier.clip(CircleShape).background(Color.LightGray),
                            content = {
                                Icon(painterResource(R.drawable.baseline_person_outline_24), "Profile", tint = Color.White, modifier = Modifier.fillMaxSize().padding(5.dp))
                            })
                    }
                    Column(Modifier.padding(16.dp)){
                        Row{
                            Text(text = "INCADAQUÉS 2020", fontSize = 25.sp, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.weight(1f))
                            Icon(painterResource(id = R.drawable.ic_heart_fill), "Favs", Modifier.size(35.dp).alpha(0.7F))
                        }
                        Text("15 - 25 OCTOBER 2020")
                        Spacer(Modifier.height(16.dp))
                    }
                }
                Row(Modifier.padding(16.dp)){
                    TopAppBarElement(icon = R.drawable.ic_calendar, text = "Program", salmonColor)
                    Spacer(modifier = Modifier.padding(5.dp))
                    TopAppBarElement(icon = R.drawable.ic_frame, text = "Artworks", darkMustardColor)
                    Spacer(modifier = Modifier.padding(5.dp))
                    TopAppBarElement(icon = R.drawable.ic_pin, text = "Map", Color.Gray)
                }
            }
        },
        bottomBar = {
            BottomAppBar {
                Row (horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()){
                    BottomAppBarElement(icon = R.drawable.ic_camera, text = "Scan")
                    BottomAppBarElement(icon = R.drawable.ic_eye, text = "Festivals")
                    BottomAppBarElement(icon = R.drawable.ic_megaphone, text = "News")
                    BottomAppBarElement(icon = R.drawable.ic_heart, text = "Favs")
                }
            }
        }){
            innerPadding ->
            // Columna amb la llista d'elements obtinguts de l'API
            LazyColumn(modifier = Modifier.padding(innerPadding), state = rememberLazyListState(0, 0)) {
                items(photographers){photographer->
                    PhotographerItem(photographer = photographer, navController)
                }
            }
    }
}

// Element que mostra la informació d'un fotògraf
@Composable
fun PhotographerItem(photographer: Photographer, navController: NavController){
    Column(modifier = Modifier.clickable {
        navController.navigate(PhotographerDetailScreen(photographer.first_name, photographer.last_name, photographer.image, photographer.description))
    }){
        AsyncImage(model = photographer.image, contentDescription = null, modifier = Modifier.height(150.dp), contentScale = ContentScale.FillWidth, placeholder = painterResource(id = R.drawable.artist_placeholder))
        Row(Modifier.padding(16.dp)){
            Text(text = photographer.first_name + " " + photographer.last_name, fontSize = 25.sp)
            Spacer(Modifier.weight(1f))
            Icon(painterResource(id = R.drawable.ic_heart), "Favs", Modifier.size(35.dp))
        }
    }
}

// Element corresponent a les icones de la barra inferior de la pantalla Home
@Composable
fun BottomAppBarElement(icon: Int, text: String){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable {}
    ){
        Icon(painterResource(id = icon), text)
        Text(text = text)
    }
}

// Element corresponent a les icones de la barra superior de la pantalla Home
@Composable
fun TopAppBarElement(icon: Int, text: String, color: Color){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.clickable {}
    ){
        IconButton(onClick = { }, modifier = Modifier.clip(CircleShape).background(color),
            content = { Icon(painterResource(id = icon), text, modifier = Modifier.fillMaxSize().padding(5.dp)) })
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    ProvaTecnicaApp2UTheme {
        HomeScreen(rememberNavController())
    }
}