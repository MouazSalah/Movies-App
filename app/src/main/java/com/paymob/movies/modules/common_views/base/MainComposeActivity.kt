package com.paymob.movies.modules.common_views.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.paymob.movies.modules.common_views.base.ui.theme.MoviesTheme
import com.paymob.movies.navigation.MoviesNavigation

class MainComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesApp {
                MoviesNavigation()
            }
        }
    }
}

@Composable
fun MoviesApp(content : @Composable ()-> Unit) {
    MoviesTheme {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoviesApp {
        MoviesNavigation()
    }
}