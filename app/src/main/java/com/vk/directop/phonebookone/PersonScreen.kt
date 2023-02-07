package com.vk.directop.phonebookone

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PersonScreen() {

    var name by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("+")
    }

    var persons by remember {
        mutableStateOf(listOf<Person>())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column() {
                OutlinedTextField(
                    value = name,
                    onValueChange = { text ->
                        name = text
                    },
                    //modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = phone.toString(),
                    onValueChange = { value ->
                        value.drop(1).toIntOrNull()?.let {
                            phone = "+$it"
                        }
                    },
                    //modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))
            Button(
                onClick = {
                    if (name.isNotBlank() && phone.drop(1).isNotBlank()) {
                        persons = persons + Person(name, phone)
                        name = ""
                        phone = "+"
                    }
                }) {
                Text(text = "Add")
            }
        }
        PersonList(names = persons)
    }
}


@Composable
fun PersonList(
    names: List<Person>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(names) { currentName ->
            Text(
                text = currentName.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {

                    }
            )
            Text(
                text = currentName.phone,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {

                    }
            )
            Divider()
        }
    }
}