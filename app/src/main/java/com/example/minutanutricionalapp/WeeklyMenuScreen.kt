package com.example.minutanutricionalapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun WeeklyMenuScreen(navController: NavController) {
    // Datos de ejemplo para las recetas semanales
    val recipes = listOf(
        Recipe( "Ensalada de Quinoa", "Una receta deliciosa y saludable con quinoa, vegetales frescos y aderezos ligeros.", R.drawable.salad_image,"Lunes"),
        Recipe("Tacos de Pollo", "Tacos de pollo con salsa casera, guacamole y más.", R.drawable.tacos_image,"Martes"),
        Recipe("Sopa de Lentejas", "Sopa rica en proteínas y fibra, ideal para una comida reconfortante.", R.drawable.soup_image,"Miércoles"),
        Recipe("Pasta con Tomate y Albahaca", "Pasta fresca con una salsa de tomate natural y albahaca.", R.drawable.pasta_image,"Jueves"),
        Recipe("Pollo al Horno", "Pollo jugoso al horno con papas y especias.", R.drawable.chicken_image,"Viernes")
    )

    // Diseño de la pantalla de recetas semanales
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título de la pantalla
        Text(
            text = "Minuta Nutricional Semanal",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Mostrar las recetas en una lista
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(recipes.size) { index ->
                RecipeCard(recipe = recipes[index])
            }
        }

        // Botón de cerrar sesión
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Navegar a la pantalla de login al cerrar sesión
                navController.navigate("login_screen") {
                    popUpTo("login_screen") { inclusive = true } // Elimina las pantallas anteriores de la pila
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Cerrar Sesión")
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Caja para la imagen y el día de la semana
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                // Imagen de la receta
                Image(
                    painter = painterResource(id = recipe.imageId),
                    contentDescription = recipe.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp),
                    alignment = Alignment.Center
                )

                // Día de la semana sobre la imagen
                Text(
                    text = recipe.dia, // Día de la semana
                    style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.7f), shape = RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Nombre de la receta
            Text(
                text = recipe.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // Descripción de la receta
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = recipe.description,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


// Datos de ejemplo para las recetas
data class Recipe(val name: String, val description: String, val imageId: Int, val dia: String)

@Preview(showBackground = true)
@Composable
fun WeeklyMenuPreview() {
    WeeklyMenuScreen(navController = NavController(LocalContext.current)) // Se puede usar un navController de prueba
}
