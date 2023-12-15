package com.amnah.store.ui.presenter.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.amnah.store.R

@Composable
fun ErrorComposable(
    tryAgain: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.error))

        LottieAnimation(
            modifier = Modifier.aspectRatio(1f),
            composition = composition,
            iterations = LottieConstants.IterateForever,
        )

        Text(
            text = "Something happened in network",
            modifier = Modifier.padding(top = 8.dp),
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.W400,
                color = MaterialTheme.colorScheme.onSurface
            )
        )

        Button(
            modifier = Modifier
                .wrapContentSize()
                .padding(vertical = 24.dp, horizontal = 24.dp)
                .background(
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.primary
                )
                .clip(RoundedCornerShape(16.dp)),
            onClick = tryAgain
        ) {

            Text(text = "Try again", textAlign = TextAlign.Center)
        }
    }
}