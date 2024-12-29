package com.example.minutanutricionalapp

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "login_screen" // Pantalla inicial
    ) {
        // Pantalla de Login
        composable("login_screen") {
            LoginScreen(
                navController = navController, // Pasamos el navController aquí
                onLoginSuccess = {
                    navController.navigate("weekly_menu_screen") {
                        popUpTo("login_screen") { inclusive = true } // Elimina las pantallas anteriores de la pila
                    }
                }
            )
        }

        // Pantalla de Registro
        composable("register_screen") {
            RegisterScreen(
                navController = navController, // Pasamos el navController aquí
                onRegisterSuccess = {
                    navController.navigate("weekly_menu_screen") {
                        popUpTo("login_screen") { inclusive = true } // Elimina las pantallas anteriores de la pila
                    }
                }
            )
        }

        // Pantalla de Recuperación de Contraseña
        composable("password_recovery_screen") {
            PasswordRecoveryScreen(
                navController = navController, // Pasamos navController aquí
                onRecoverySuccess = {
                    // Después de la recuperación exitosa, navega al login
                    navController.navigate("login_screen") {
                        popUpTo("login_screen") { inclusive = true } // Elimina las pantallas anteriores de la pila
                    }
                }
            )
        }

        // Pantalla de Minuta Nutricional Semanal
        composable("weekly_menu_screen") {
            WeeklyMenuScreen(navController = navController) // Pasar navController a WeeklyMenuScreen
        }
    }
}



