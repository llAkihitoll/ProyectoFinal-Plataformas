package com.example.proyecto.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp



@Composable
fun RatingDropdown(label: String, onValueSelected: (Int) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(-1) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = label)
        Spacer(modifier = Modifier.height(4.dp))
        Box {
            OutlinedTextField(
                value = if (selectedIndex == -1) "" else (selectedIndex + 1).toString(),
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true },
                enabled = false,
                readOnly = true,
                trailingIcon = {
                    Icon(
                        imageVector = if (expanded) Icons.Default.ArrowDropUp else Icons.Default.ArrowDropDown,
                        contentDescription = null
                    )
                }
            )
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                (1..10).forEachIndexed { index, number ->
                    DropdownMenuItem(
                        text = { Text(number.toString()) },
                        onClick = {
                            selectedIndex = index
                            onValueSelected(index)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

