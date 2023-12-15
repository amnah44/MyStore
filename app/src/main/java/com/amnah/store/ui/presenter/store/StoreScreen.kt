package com.amnah.store.ui.presenter.store

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.amnah.store.R
import com.amnah.store.data.model.ProductItemDto
import com.amnah.store.ui.presenter.component.ErrorComposable
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun StoreScreen(
    storeViewModel: StoreViewModel = koinViewModel(),
) {


    val state by storeViewModel.state.collectAsState()
    val effect by storeViewModel.effect.collectAsState(initial = null)
    val context = LocalContext.current


    StoreContent(
        state = state,
        tryAgain = storeViewModel::getAllProducts
    )


    LaunchedEffect(key1 = Unit) {
        storeViewModel.effect.collectLatest {
            onEffect(effect, context)
        }
    }
}

private fun onEffect(effect: StoreUIEffect?, context: Context) {
    when (effect) {
        StoreUIEffect.StoreError -> {}
        else -> {}
    }
}


@Composable
private fun StoreContent(
    state: StoreUIState,
    tryAgain: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }
        if (state.isError) {
            ErrorComposable(tryAgain)
        }
        if (state.isSuccess) {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            )
            {
                items(state.products) {
                    ProductCard(product = it)
                }
            }
        }
    }
}

@Composable
private fun ProductCard(
    product: ProductItemDto,
) {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Toast
                    .makeText(context, product.title.toString(), Toast.LENGTH_LONG)
                    .show()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubcomposeAsyncImage(
            model = product.image,
            contentDescription = product.title,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(16.dp))
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(16.dp),
                    color = MaterialTheme.colorScheme.primary
                ),
            contentScale = ContentScale.Crop,
            loading = {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(8.dp)
                )
            },
            error = {
                Image(
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = "error_state",
                    modifier = Modifier
                        .size(24.dp)
                        .padding(16.dp)
                )
            }
        )


        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = product.title ?: "", style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
            )

            Text(
                text = product.price.toString() + " $", style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .fillMaxWidth()
            )
        }

    }

}
