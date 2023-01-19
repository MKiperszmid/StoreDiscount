package com.mkiperszmid.storediscount

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mkiperszmid.storediscount.cart.presentation.CartScreen
import com.mkiperszmid.storediscount.home.presentation.HomeScreen
import com.mkiperszmid.storediscount.ui.theme.StoreDiscountTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoreDiscountTheme {
                val navController = rememberNavController()
                BackdropScaffold(
                    appBar = { },
                    backLayerContent = { },
                    peekHeight = 1.dp,
                    frontLayerContent = {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            NavHost(navController = navController, startDestination = "home") {
                                composable("home") {
                                    HomeScreen() {
                                        navController.navigate("cart")
                                    }
                                }

                                composable("cart") {
                                    CartScreen()
                                }
                            }
                        }
                    }
                )
            }
        }
    }
}
