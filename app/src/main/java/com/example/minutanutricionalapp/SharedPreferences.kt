package com.example.minutanutricionalapp

import android.content.Context
import android.content.SharedPreferences

class AuthHelper(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserAuth", Context.MODE_PRIVATE)

    // Guardar las credenciales de usuario (usuario y contraseña)
    fun saveCredentials(username: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("password", password)
        editor.apply()
    }

    // Actualizar la contraseña
    fun updatePassword(newPassword: String) {
        val editor = sharedPreferences.edit()
        editor.putString("password", newPassword)
        editor.apply()
    }

    // Obtener el nombre de usuario guardado
    fun getUsername(): String? {
        return sharedPreferences.getString("username", null)
    }

    // Obtener la contraseña guardada
    fun getPassword(): String? {
        return sharedPreferences.getString("password", null)
    }

    // Verificar si las credenciales están almacenadas
    fun isUserLoggedIn(): Boolean {
        return getUsername() != null && getPassword() != null
    }

    // Limpiar las credenciales (cerrar sesión)
    fun clearCredentials() {
        val editor = sharedPreferences.edit()
        editor.remove("username")
        editor.remove("password")
        editor.apply()
    }
}

