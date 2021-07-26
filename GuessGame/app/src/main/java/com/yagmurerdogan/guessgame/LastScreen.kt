package com.yagmurerdogan.guessgame

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun LastScreen(navController: NavController, incomingResult: Boolean) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        if (incomingResult) {
            Text(
                text = "You win!",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
            Image(painter = painterResource(id = R.drawable.ic_happy), contentDescription = null)

        } else {
            Text(
                text = "You lost!",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold
            )
            Image(painter = painterResource(id = R.drawable.ic_sad), contentDescription = null)

        }
    }
}