import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.NavController
import com.facebook.shimmer.Shimmer
import com.paymob.movies.R
import com.paymob.movies.core.theme.AppColors
import com.paymob.movies.core.theme.boldRobotoFonts
import com.paymob.movies.core.theme.mediumRobotoFonts
import com.paymob.movies.core.theme.regularRobotoFonts
import com.paymob.movies.modules.listing.domain.entites.MovieEntity
import com.paymob.movies.navigation.MoviesScreens

@Composable
fun MoviesListingComposeScreen(navController: NavController) {
    // Counter to check how many times the screen is recomposed
    var recompositionCounter = 0
    recompositionCounter++

    // Log to check recomposition count
    println("MoviesComposeApp listing screen is recomposed: $recompositionCounter times")

    // Lifecycle Observer (same as before)
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> println("MoviesComposeApp Screen is ON_RESUME")
                Lifecycle.Event.ON_CREATE -> println("MoviesComposeApp Screen is ON_CREATE")
                Lifecycle.Event.ON_START -> println("MoviesComposeApp Screen is ON_START")
                Lifecycle.Event.ON_PAUSE -> println("MoviesComposeApp Screen is ON_PAUSE")
                Lifecycle.Event.ON_STOP -> println("MoviesComposeApp Screen is ON_STOP")
                Lifecycle.Event.ON_DESTROY -> println("MoviesComposeApp Screen is ON_DESTROY")
                Lifecycle.Event.ON_ANY -> println("MoviesComposeApp Screen is ON_ANY")
                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    LaunchedEffect(Unit) {
        println("MoviesComposeApp listing screen is opened")
    }

    MoviesListingScreenContents(
        onItemClick = {
            navController.navigate(MoviesScreens.MoviesDetailsScreen.name)
        }
    )
}

@Composable
fun MoviesListingScreenContents(onItemClick: () -> Unit){
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MovieListViewItemCard(MovieEntity(
                id = 1,
                movieName = "Mouaz Salah",
                moviePoster = "djnfjkf",
                releaseDate = "12-13-2442",
                rating = 0.0f,
                isFavorite = false
            ))
        }
    }
}

@Composable
fun MovieListViewItemCard(movie : MovieEntity) {
    Card(
        modifier = Modifier
            .padding(all = 16.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()) {
        Column (
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            Image(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(8.dp)
                    ),
                contentScale = ContentScale.FillWidth,
                painter = painterResource(id = R.drawable.img_movie_poster), contentDescription = "Movie Poster Description")
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Deadpool & Wolverine",
                color = AppColors.Primary,
                fontFamily = boldRobotoFonts,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            SpannableText(title = "Release Date: ", description = "2024-07-24")
            Spacer(modifier = Modifier.height(8.dp))
            SpannableText(title = "Rating: ", description = "7.752")
        }
    }
}

@Composable
fun SpannableText(title: String, description : String) {

   BasicText(text = AnnotatedString.Builder().apply {
        withStyle(
            style = SpanStyle(
               color = AppColors.colorDarkGrey,
               fontFamily = regularRobotoFonts,
               fontSize = 13.sp
            )
        ) {
            append(title)
        }

       Spacer(modifier = Modifier.width(12.dp))

       withStyle(
           style = SpanStyle(
               color = AppColors.Primary,
               fontFamily = mediumRobotoFonts,
               fontSize = 14.sp
           )
       ){
           append(description)
       }
   }.toAnnotatedString())

}

@Preview(showBackground = true)
@Composable
fun MoviesListingPreview(){
    MoviesListingScreenContents(onItemClick = {
        println("item is clicked")
    })
}

@Composable
fun MovieDetailsComposeScreen()
{
    println("MoviesComposeApp details screen is opened")
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
        contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Movie Details Screen"
            )
        }
    }
}