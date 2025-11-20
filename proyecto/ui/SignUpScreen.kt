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
fun SignUpScreen(
    navController: NavController,
    onSignUpSuccess: () -> Unit
) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }

    val title = stringResource(R.string.register_title)
    val usernameLabel = stringResource(R.string.username_label)
    val registerButton = stringResource(R.string.register_button)
    val goBackLogin = stringResource(R.string.back_to_login)
    val errorBlank = stringResource(R.string.error_username_blank)
    val errorUserExists = stringResource(R.string.error_user_exists)

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
                when {
                    username.text.isBlank() -> {
                        errorMessage = errorBlank
                    }
                    UserSession.userExists(username.text) -> {
                        errorMessage = errorUserExists
                    }
                    else -> {
                        UserSession.register(username.text)
                        onSignUpSuccess()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(registerButton)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            navController.popBackStack()
        }) {
            Text(goBackLogin)
        }
    }
}
