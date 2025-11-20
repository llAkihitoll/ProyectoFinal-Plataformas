package com.example.proyecto.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyecto.R
import com.example.proyecto.navigation.AppScreen
import com.example.proyecto.ui.components.RatingDropdown

@Composable
fun MainScreen(
    navController: NavController,
    onRestClick: () -> Unit = {}
) {
    var showFoodMenu by remember { mutableStateOf(false) }
    var showMoodMenu by remember { mutableStateOf(false) }
    var showWitMenu by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.menu_background),
            contentDescription = stringResource(R.string.background),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Botón de perfil en la esquina superior derecha
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .clickable {
                    navController.navigate(AppScreen.Profile.route)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = stringResource(R.string.profile_icon),
                modifier = Modifier.size(48.dp)
            )
        }

        // Botones del menú
        Column(
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 32.dp, start = 32.dp, end = 32.dp)
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                MenuButton(R.drawable.wit, stringResource(R.string.how_important)) { showWitMenu = true }
                MenuButton(R.drawable.mood, stringResource(R.string.how_enjoyable)) { showMoodMenu = true }
            }
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                MenuButton(R.drawable.food, stringResource(R.string.how_healthy)) { showFoodMenu = true }
                MenuButton(R.drawable.rest, stringResource(R.string.rest)) { onRestClick() }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Personaje fijo sin lógica
            Image(
                painter = painterResource(id = R.drawable.face_neutral),
                contentDescription = stringResource(R.string.character_face),
                modifier = Modifier
                    .size(220.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

        // Menú Food
        if (showFoodMenu) {
            MenuOverlay(onDismiss = { showFoodMenu = false }) {
                RatingDropdown(label = stringResource(R.string.how_healthy)) { _ -> }
                Spacer(modifier = Modifier.height(16.dp))
                RatingDropdown(label = stringResource(R.string.how_enjoyable)) { _ -> }
                Spacer(modifier = Modifier.height(24.dp))
                MenuButtonAction(text = stringResource(R.string.done)) {
                    showFoodMenu = false
                }
            }
        }

        // Menú Mood
        if (showMoodMenu) {
            MenuOverlay(onDismiss = { showMoodMenu = false }) {
                RatingDropdown(label = stringResource(R.string.how_enjoyable)) { _ -> }
                Spacer(modifier = Modifier.height(24.dp))
                MenuButtonAction(text = stringResource(R.string.done)) {
                    showMoodMenu = false
                }
            }
        }

        // Menú Wit
        if (showWitMenu) {
            MenuOverlay(onDismiss = { showWitMenu = false }) {
                RatingDropdown(label = stringResource(R.string.how_important)) { _ -> }
                Spacer(modifier = Modifier.height(16.dp))
                RatingDropdown(label = stringResource(R.string.how_enjoyable)) { _ -> }
                Spacer(modifier = Modifier.height(24.dp))
                MenuButtonAction(text = stringResource(R.string.done)) {
                    showWitMenu = false
                }
            }
        }
    }
}

@Composable
fun MenuOverlay(onDismiss: () -> Unit, content: @Composable ColumnScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x99000000))
            .clickable { onDismiss() },
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .clickable(enabled = false) {}
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(12.dp))
                .padding(24.dp)
                .width(300.dp),
            content = content
        )
    }
}

@Composable
fun MenuButton(
    imageRes: Int,
    contentDescription: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                Color.White.copy(alpha = 0.2f),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = contentDescription,
            modifier = Modifier.size(72.dp)
        )
    }
}

@Composable
fun MenuButtonAction(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text)
    }
}
