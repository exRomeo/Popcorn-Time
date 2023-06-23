package com.example.popcorntime.presentation.common

import androidx.annotation.RawRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.popcorntime.R

@Composable
fun Error(message: String, @RawRes animation: Int) {
    Text(text = message)
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(
                animation
            )
        )
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
        Text(text = message)
        Text(
            text = stringResource(id = R.string.try_again),
            style = TextStyle(fontWeight = FontWeight.SemiBold)
        )

    }
}

@Composable
fun NoNetwork(@RawRes animation: Int) {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(
            spec = LottieCompositionSpec.RawRes(
                animation
            )
        )
        LottieAnimation(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
        Text(
            text = stringResource(id = R.string.not_connected),
            style = TextStyle(fontWeight = FontWeight.SemiBold)
        )

    }
}


@Composable
fun PosterImageError() {
    Box(
        Modifier
            .aspectRatio(2 / 3f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.error_placeholder),
            contentDescription = stringResource(id = R.string.error)
        )
    }
}

@Composable
fun BackDropImageError() {
    Box(
        Modifier
            .aspectRatio(1.778f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.error_placeholder),
            contentDescription = stringResource(id = R.string.error)
        )
    }
}