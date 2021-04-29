package ru.agladkov.podlodka_home_task

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import ru.agladkov.podlodka_home_task.navigation.NavCommand
import ru.agladkov.podlodka_home_task.ui.theme.PodlodkaComposeHomeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PodlodkaComposeHomeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavCommand.Main.title
                    ) {
                        composable(NavCommand.Main.title) { MainScreen(navController) }
                        composable(NavCommand.Details.title) {
                            Log.e("TAG", "${navController.previousBackStackEntry?.arguments}")
                            (navController.previousBackStackEntry?.arguments?.getParcelable<DetailsScreenModel>("model"))?.let {
                                DetailsScreen(it, navController)
                            }
                        }
                    }
                }
            }
        }
    }
}