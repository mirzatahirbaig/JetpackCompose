package com.mobizion.statemanagement.composables

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp

/**
 *@Author: Mirza Tahir Baig
 *@Email: tahir@mobizion.com
 *@Date: 24/10/2022
 */

@Composable
fun GreetingsScreen() {
    val greetingList = remember { mutableStateListOf<String>() }
    var greeting by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingsList(greetingList = greetingList)
        AddGreetingsMessage(greeting = greeting, onValueChange = { greeting = it }, onClick = {
            if (greeting.isNotEmpty()) {
                greetingList.add(greeting)
                greeting = ""
            }
        })
    }


}

@Composable
private fun AddGreetingsMessage(greeting: String, onValueChange: (String) -> Unit, onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = greeting,
            onValueChange = onValueChange,
            label = { Greetings(name = "Name") })
        Button(onClick = onClick) {
            Greetings(name = "Add")
        }
    }
}

@Composable
private fun GreetingsList(greetingList: List<String>) {
    val list = greetingList.distinct()
    LazyColumn {
        items(list) { greetings ->
            Greetings(name = greetings)
        }
    }
}

@Composable
private fun Greetings(name: String) {
    Text(text = name)
}