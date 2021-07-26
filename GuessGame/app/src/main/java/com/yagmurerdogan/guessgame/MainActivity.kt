package com.yagmurerdogan.guessgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.yagmurerdogan.guessgame.ui.theme.GuessGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GuessGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NavigateScreen()
                }
            }
        }
    }
}

@Composable
fun NavigateScreen() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "main_screen"
    ) {
        composable("main_screen") {
            MainScreen(navController = navController)
        }
        composable("guess_screen") {
            GuessScreen(navController = navController)
        }
        composable("last_screen/{sonuc}",
            arguments = listOf(
                navArgument("sonuc") { type = NavType.BoolType }
            )) {
            val sonuc = it.arguments?.getBoolean("sonuc")!!
            LastScreen(navController = navController, sonuc)
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Guess Game",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
        Image(
            painter = painterResource(id = R.drawable.ic_casino),
            contentDescription = null
        )
        Button(
            onClick = {
                navController.navigate("guess_screen")
            },
            modifier = Modifier.size(150.dp, 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
        ) {
            Text(
                text = "Start Game",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GuessGameTheme {
        NavigateScreen()
    }
}