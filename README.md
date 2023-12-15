## Project Overview

This Android app is built using Jetpack Compose, following the principles of Clean Code and MVVM design pattern. It aims to fetch and display a list of products from a public API and provide a clean, maintainable, and efficient codebase.

## My Demo
 https://youtube.com/shorts/FSoeRu2KbMg

## Technology Stack

- **Jetpack Compose**: Modern Android UI toolkit for building native UIs.
- **AndroidX Libraries**: Core functionality for Android apps.
- **ViewModel**: Part of Android Architecture Components, it manages UI-related data.
- **Lifecycle**: Android Architecture Components that provides lifecycle-aware components.
- **Ktor_Client**: HTTP client for making network requests.
- **Gson**: Library for JSON serialization and deserialization.
- **Koin**: Dependency injection library.
- **Coroutines**: Library for managing background threads.
- **Coil**: Image loading library for Android.
- **Lottie**: A pleace holder for animtion image as svg.

## Installation Guide

Follow these steps to set up and run the app:

1. Clone the repository:

```bash
  https://github.com/amnah44/MyStore.git
```

2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device.

## Implementation Details
### 1. Data Model
A data model Product is created to represent the product with fields for name, price, and image URL.
```kotlin
@Serializable
data class ProductItemDto(
    @SerialName("category")
    val category: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("id")
    val id: Int?,
    @SerialName("image")
    val image: String?,
    @SerialName("price")
    val price: Double?,
    @SerialName("rating")
    val ratingDto: RatingDto?,
    @SerialName("title")
    val title: String?
)
```
### 2. Network Request
An interface ProductApi is implemented to fetch a list of products from the provided API endpoint using ktor client.

```kotlin
interface IProductsRemoteApi {

    suspend fun getProducts(): List<ProductItemDto>
}

class ProductsRemoteApi(
    client: HttpClient,
) : IProductsRemoteApi, BaseRemoteApi(client) {

    override suspend fun getProducts(): List<ProductItemDto> {
        val result = tryToExecute<List<ProductItemDto>> {
            get("/products/")
        }
        return result
    }
    
}
```

### 3. Injection 
The Dependency Injection (DI) package in this project is responsible for managing dependencies throughout the application. It is implemented using Koin, a pragmatic lightweight dependency injection framework for Kotlin developers.


 - **AppModule**: which includes all other modules
```kotlin
   
fun appModule() = module {
    includes(
        viewModelModule,
        GatewayModule,
        NetworkModule,
    )
}
   
```

 - **ViewModelModule**: This module is responsible for providing ViewModel dependencies. It includes all the ViewModel classes used in the application.

 - **GatewayModule**: This module is responsible for providing gateway dependencies. It includes all the gateway interfaces and their implementations used in the application

 - **NetworkModule**: This module is responsible for providing network dependencies. It includes all the network-related classes like Retrofit instances, API interfaces, and their implementations. 


### 4. Injection Usage
To use these modules, they need to be loaded into the Koin application instance at the start of the application. This is typically done in the Application class.

```kotlin
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule())
        }
    }
}

```

### 5. Product Screen
A Compose UI screen is created to display the list of products. Each item in the list displays the product name, price, and an image, also the state network in sample impelement.
```kotlin
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
```
