package com.aacd.posterday.android

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aacd.posterday.android.models.Poster
import com.aacd.posterday.android.ui.screens.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Navigation(auth: FirebaseAuth) {
    val navController = rememberNavController()
    val _auth = FirebaseAuth.getInstance();
    NavHost(navController = navController , startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            //val viewModel = viewModel<MenuViewModel>();
            LoginScreen(navController = navController, modifier = Modifier,auth = _auth)
        }
        composable(
            route = Screen.DetailScreen.route +"/{teamId}" ,//or use"?name={name}" for optional
            arguments = listOf(
                navArgument(name = "teamId") {
                    type = NavType.StringType
                    defaultValue = "tid"
                }, navArgument(name = "projectName") {
                    type = NavType.StringType
                    defaultValue = "project name"
                }, navArgument(name = "teamName") {
                    type = NavType.StringType
                    defaultValue = "team name"
                }
            )
        ) { entry ->
            DetailScreen(navController = navController,
                teamId = entry.arguments?.getString("teamId")!!,
                projectName = entry.arguments?.getString("projectName")!!,
                teamName = entry.arguments?.getString("teamName")!!,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        composable(
            route = Screen.MainMenu.route
        ){ entry ->
            MainMenu(navController = navController,
                modifier = Modifier
                    .fillMaxWidth(),
                auth = _auth
            )

        }
        composable(
            route = Screen.RegisterScreen.route
        ){ entry ->
            RegisterScreen(navController = navController,
                modifier = Modifier
                    .fillMaxWidth(),
            )

        }
        composable(
            route = Screen.PostersScreen.route
        ){ entry ->
            PostersScreen(navController = navController,
                modifier = Modifier
                    .fillMaxWidth(),
                posterList = listOf(Poster("oeir84","green","project name","comp sci"),Poster("osnieo53","d","d","d"))
            )
        }
        composable(
            route = Screen.InfoScreen.route
        ){ entry ->
            InfoScreen(navController = navController,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        }

    }
}
