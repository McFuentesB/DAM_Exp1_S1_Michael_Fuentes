package com.example.minutanutricionalapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PasswordRecoveryScreen(navController: NavController, onRecoverySuccess: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") } // Nueva contraseña
    var confirmPassword by remember { mutableStateOf("") } // Confirmar nueva contraseña
    var errorMessage by remember { mutableStateOf("") }

    val authHelper = AuthHelper(LocalContext.current) // Obtenemos la instancia de AuthHelper

    // Función para validar y actualizar la contraseña
    fun validateAndRecover() {
        errorMessage = ""

        // Validación de correo electrónico
        if (email.isEmpty()) {
            errorMessage = "Por favor, ingresa tu correo electrónico"
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMessage = "Por favor, ingresa un correo electrónico válido"
        } else if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            errorMessage = "Por favor, ingresa y confirma tu nueva contraseña"
        } else if (newPassword != confirmPassword) {
            errorMessage = "Las contraseñas no coinciden"
        } else {
            // Aquí, validar que el correo coincide con un usuario registrado, y si es válido, actualizar la contraseña
            // Actualizar la contraseña en SharedPreferences
            authHelper.updatePassword(newPassword)
            onRecoverySuccess() // Simulamos que la recuperación fue exitosa
        }
    }

    // Diseño de la pantalla de recuperación
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        // Título
        Text(
            text = "Recuperar Contraseña",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo de correo electrónico
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo de nueva contraseña
        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text("Nueva Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation() // Para ocultar la contraseña
        )

        // Campo para confirmar la nueva contraseña
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation() // Para ocultar la contraseña
        )

        // Mostrar mensaje de error si lo hay
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de recuperación
        Button(
            onClick = { validateAndRecover() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Recuperar Contraseña")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Enlace para ir al Login
        TextButton(onClick = { navController.navigate("login_screen") }) {
            Text("Volver al Login")
        }
    }
}
