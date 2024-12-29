package com.example.minutanutricionalapp

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController: NavHostController, onLoginSuccess: () -> Unit) {
    // Variables para guardar usuario y contraseña
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    // Instanciar el helper de autenticación
    val authHelper = remember { AuthHelper(navController.context) }

    // Función para manejar el inicio de sesión
    fun handleLogin() {
        // Verificamos si el usuario y contraseña son correctos
        val storedUsername = authHelper.getUsername()
        val storedPassword = authHelper.getPassword()

        if (username == storedUsername && password == storedPassword) {
            // Si las credenciales son correctas, navegamos a la pantalla de minuta
            onLoginSuccess()
        } else {
            // Si las credenciales no coinciden, mostramos un mensaje de error
            errorMessage = "Usuario o contraseña incorrectos"
        }
    }

    // Diseño de la pantalla
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Título
        Text(
            text = "Iniciar Sesión",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo de usuario
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar mensaje de error si las credenciales son incorrectas
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        // Botón de inicio de sesión
        Button(
            onClick = {
                handleLogin()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Enlace para ir al registro
        TextButton(onClick = {
            navController.navigate("register_screen") // Navegar a la pantalla de registro
        }) {
            Text("¿No tienes cuenta? Regístrate aquí")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Enlace para recuperar contraseña
        TextButton(onClick = {
            navController.navigate("password_recovery_screen") // Navegar a la pantalla de recuperación de contraseña
        }) {
            Text("¿Olvidaste tu contraseña?")
        }
    }
}
