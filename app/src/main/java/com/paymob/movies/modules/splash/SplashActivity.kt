package com.paymob.movies.modules.splash

import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.paymob.movies.modules.common_views.base.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import android.content.Intent
import com.paymob.movies.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.paymob.movies.core.theme.MoviesTheme
import com.paymob.movies.modules.common_views.base.MainComposeActivity

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val SPLASH_DURATION_MS : Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoviesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.White)
                            .width(350.dp)
                            .padding(innerPadding),
                        contentAlignment = Alignment.Center // Center the content inside the Box
                    )
                    {
                        Image(
                            painter = painterResource(id = R.drawable.paymob_logo),
                            contentDescription = "App Logo in splash Screen",
                            Modifier.fillMaxWidth(2f / 3f) // Take 2/3 of the screen width
                                .aspectRatio(1f) // Maintain aspect ratio of the image (you can adjust this if needed)
                        )
                    }
                }
            }
        }

        lifecycleScope.launch {
            delay(3000)
            delay(SPLASH_DURATION_MS)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}