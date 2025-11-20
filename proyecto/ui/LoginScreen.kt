package com.example.proyecto.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.res.stringResource
import com.example.proyecto.R
import com.example.proyecto.data.UserSession

@Composable
fun LoginScreen(
    navController: NavController,
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }

    val title = stringResource(R.string.login_title)
    val usernameLabel = stringResource(R.string.username_label)
    val loginButtonText = stringResource(R.string.login_button)
    val registerPrompt = stringResource(R.string.prompt_register)
    val errorUserNotFound = stringResource(R.string.error_user_not_found)
    val errorUsernameBlank = stringResource(R.string.error_username_blank)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(usernameLabel) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = {
                if (username.text.isNotBlank()) {
                    if (UserSession.login(username.text)) {
                        errorMessage = ""
                        onLoginSuccess()
                    } else {
                        errorMessage = errorUserNotFound
                    }
                } else {
                    errorMessage = errorUsernameBlank
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(loginButtonText)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = { onRegisterClick() }) {
            Text(registerPrompt)
        }
    }
}
