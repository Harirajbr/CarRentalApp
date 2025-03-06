package com.example.carrental

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.carrental.ui.theme.CarRentalTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange


import java.util.Calendar



import android.app.DatePickerDialog

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.ui.Alignment



import androidx.compose.ui.text.input.KeyboardType

import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CarRentalTheme {
                CarRentalScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarRentalScreen() {
    val context = LocalContext.current
    var pickupLocation by remember { mutableStateOf(TextFieldValue("")) }
    var dropoffLocation by remember { mutableStateOf(TextFieldValue("")) }
    var pickupDate by remember { mutableStateOf("") }
    var dropoffDate by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Car Rental", fontSize = 22.sp, modifier = Modifier.padding(start = 16.dp)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .background(Color(0xFFF5F5F5))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp), // Ensures the form is visible above the button
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = pickupLocation,
                            onValueChange = { pickupLocation = it },
                            label = { Text("Pickup Location") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = dropoffLocation,
                            onValueChange = { dropoffLocation = it },
                            label = { Text("Drop-off Location (Optional)") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        DatePickerField(label = "Pickup Date", date = pickupDate) { pickupDate = it }
                        Spacer(modifier = Modifier.height(8.dp))
                        DatePickerField(label = "Drop-off Date", date = dropoffDate) { dropoffDate = it }
                    }
                }
            }

            // Search Button - Placed at the bottom of the screen
            Button(
                onClick = {
                    if (pickupLocation.text.isBlank() || pickupDate.isBlank()) {
                        Toast.makeText(context, "Pickup location and date are required!", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    val kayakUrl =
                        "https://www.kayak.com/in?a=awesomecars&url=/cars/${pickupLocation.text}/${dropoffLocation.text.ifBlank { " " }}/${pickupDate}/${dropoffDate}"

                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(kayakUrl))
                    context.startActivity(intent)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .align(Alignment.BottomCenter), // Moves button to bottom
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
            ) {
                Text("SEARCH", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun DatePickerField(label: String, date: String, onDateSelected: (String) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    OutlinedTextField(
        value = date,
        onValueChange = { },
        label = { Text(label) },
        readOnly = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        trailingIcon = {
            IconButton(onClick = {
                DatePickerDialog(context, { _, selectedYear, selectedMonth, selectedDay ->
                    val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                    onDateSelected(selectedDate)
                }, year, month, day).show()
            }) {
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "Pick Date")
            }
        }
    )
}