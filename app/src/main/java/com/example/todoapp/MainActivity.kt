package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todoapp.models.Todo
import com.example.todoapp.ui.theme.TodoAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoApp()
                }
            }
        }
    }
}


@Composable
fun TodoApp(){
    var todoList = remember {
        mutableStateListOf<Todo>()
    }
    Column() {
        TodoInput(todoList = todoList)
        TodoList(todoList = todoList)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoInput(todoList: MutableList<Todo>){
    var newTask by remember { mutableStateOf("") }
    Row(
        Modifier.padding(10.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(value = newTask, onValueChange = {newTask = it}, label = { Text(text = "Add TODO")})
        Button( onClick = { todoList.add(0, Todo(id = todoList.count(), text = newTask)); newTask = ""  }, Modifier.padding(horizontal = 16.dp)) {
            Text(text = "+")
        }
    }
}

@Composable
fun TodoList(todoList: MutableList<Todo>){
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn(){
            items(todoList){
                    todo -> Row(
                Modifier.padding(8.dp).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = todo.text)
                Button(colors = ButtonDefaults.buttonColors(contentColor = Color.Red, containerColor = Color.White),onClick = { todoList.remove(todo) }) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete",)
                }
            }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoAppTheme {
        TodoApp()
    }
}