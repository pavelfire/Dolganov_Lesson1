package com.vk.directop.phonebookone

import android.content.Context
import android.widget.Toast
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun PersonScreen(context: Context) {

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
        OutlinedTextField(
            value = name,
            onValueChange = { text ->
                name = text
            },
            label = {
                Text("Name")
            }
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = phone,
                onValueChange = { value ->
                    value.drop(1).toIntOrNull()?.let {
                        phone = "+$it"
                    }
                },
                label = {
                    Text("Phone")
                }
            )

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
        PersonList(context = context, names = persons)
    }
}


@Composable
fun PersonList(
    context: Context,
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
                        Toast.makeText(context, "Name is ${currentName.name}", Toast.LENGTH_SHORT).show()
                    },
                color = when(currentName.name.length){
                    1 -> Color.Red
                    2 -> Color.Black
                    3 -> Color.Cyan
                    4 -> Color.Green
                    5 -> Color.Magenta
                    7 -> Color.Gray
                    else -> Color.DarkGray
                }
            )
            Text(
                text = currentName.phone,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        Toast.makeText(context, "To do call to ${currentName.phone}", Toast.LENGTH_LONG).show()
                    }
            )
            Divider()
        }
    }
}