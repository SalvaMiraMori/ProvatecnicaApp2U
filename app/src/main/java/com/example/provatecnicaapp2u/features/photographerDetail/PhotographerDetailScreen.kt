package com.example.provatecnicaapp2u.features.photographerDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.provatecnicaapp2u.R
import com.example.provatecnicaapp2u.ui.theme.ProvaTecnicaApp2UTheme
import kotlinx.serialization.Serializable

@Serializable
data class PhotographerDetailScreen (
    val firstName: String,
    val lastName: String,
    val image: String,
    val description: String
)

@Composable
fun PhotographerDetailScreen(navController: NavController, firstName: String, lastName: String, image: String, description: String) {
    Column{

        // Crea el botó que permet tornar a la pantalla Home
        Row(modifier = Modifier.height(60.dp).padding(15.dp)){
            Icon(painter = painterResource(id = R.drawable.ic_back_arrow), contentDescription = null, modifier = Modifier.size(30.dp).clickable {
                navController.popBackStack()
            })
        }

        // Mostra la imatge del fotògraf
        AsyncImage(model = image, contentDescription = null, modifier = Modifier.height(200.dp), contentScale = ContentScale.FillWidth)

        // Mostra elels nom i cognoms del fotògraf
        Row(Modifier.padding(16.dp)){
            Text(text = "$firstName $lastName", fontSize = 25.sp)
            Spacer(Modifier.weight(1f))
            Icon(painterResource(id = R.drawable.ic_heart), "Favs", Modifier.size(35.dp))
        }

        // Mostra la descripció del fotògraf
        Text(description, modifier = Modifier.padding(16.dp).verticalScroll(rememberScrollState()))
    }
}

@Preview(showBackground = true)
@Composable
fun PhotographerDetailScreenPreview() {
    ProvaTecnicaApp2UTheme {
        PhotographerDetailScreen(rememberNavController(), "Salva", "Mira", "https://inphototest.app2u.es/media/photographs_images/1f7cf7b2-52a7-447a-861a-79e7d9149898.jpeg", "Potato")
    }
}