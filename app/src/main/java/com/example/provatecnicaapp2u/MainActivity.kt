package com.example.provatecnicaapp2u

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.provatecnicaapp2u.features.navigation.AppNavigation
import com.example.provatecnicaapp2u.ui.theme.ProvaTecnicaApp2UTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProvaTecnicaApp2UTheme {
                AppNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProvaTecnicaApp2UTheme {
        AppNavigation()
    }
}