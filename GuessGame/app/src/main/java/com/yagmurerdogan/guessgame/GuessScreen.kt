package com.yagmurerdogan.guessgame

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.random.Random

@Composable
fun GuessScreen(navController: NavController) {

    val finalGuess = remember { mutableStateOf("") }
    val chance = remember { mutableStateOf(5) }
    val helperText = remember { mutableStateOf("Give a number") }
    val randomNumber = remember { mutableStateOf(5) }

    LaunchedEffect(key1 = true) {
        randomNumber.value = Random.nextInt(101) // 0-100
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Chance: ${chance.value}",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
        Text(
            text = "Help: ${helperText.value}",
            fontSize = 24.sp,
            color = Color.Black
        )
        TextField(
            value = finalGuess.value,
            onValueChange = {
                finalGuess.value = it
            },
            label = {
                Text(text = "Guess")
            }
        )
        Button(
            onClick = {
                chance.value = chance.value - 1
                val predictedNumber = finalGuess.value.toInt()

                if (predictedNumber == randomNumber.value) {
                    navController.navigate("last_screen/true") {
                        popUpTo("guess_screen") {
                            inclusive = true
                        } //write screen id that you want to delete from backstack
                    }
                    return@Button

                }
                if (predictedNumber > randomNumber.value) {
                    helperText.value = "Decrease"
                }
                if (predictedNumber < randomNumber.value) {
                    helperText.value = "Increase"
                }
                if (chance.value == 0) {
                    navController.navigate("last_screen/false") {
                        popUpTo("guess_screen") {
                            inclusive = true
                        } //write screen id that you want to delete from backstack
                    }
                }
                finalGuess.value = ""
            },
            modifier = Modifier.size(150.dp, 50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
        ) {
            Text(
                text = "Guess",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}