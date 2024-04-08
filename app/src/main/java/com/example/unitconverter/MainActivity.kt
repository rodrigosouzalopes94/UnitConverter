package com.example.unitconverter

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}



@Composable
fun UnitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Centimeters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(0.01) }
    val oConversionFactor = remember { mutableStateOf(0.01) }

    val customTextStyle = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 16.sp,
        color = Color.Red
    )





    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() / 1
        outputValue = result.toString()
    }




        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Unit Converter", style = customTextStyle)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = inputValue, onValueChange = {
                inputValue = it

            },
                label ={Text(text = "Enter value")})
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Box {
                    Button(onClick = { iExpanded = true }) {
                        Text(text = inputUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")

                    }

                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                        DropdownMenuItem(text = { Text("Centimeters") }, onClick = {iExpanded = false
                            inputUnit = "centimeters, "
                            conversionFactor.value = 0.01
                            convertUnits()
                        })
                        DropdownMenuItem(text = { Text("Meters") }, onClick = { iExpanded = false
                            inputUnit = "meters"
                            conversionFactor.value = 1.0
                            convertUnits()
                        })


                        DropdownMenuItem(text = { Text("Feet") }, onClick = { iExpanded = false
                            inputUnit = "Feet"
                            conversionFactor.value = 0.3048
                            convertUnits()
                        })
                        DropdownMenuItem(text = { Text("Milimeters") }, onClick = {iExpanded = false
                        inputUnit = "Milimeters"
                        conversionFactor.value = 0.01
                        convertUnits()
                        })
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))

                Box {
                    Button(onClick = {oExpanded = true }) {
                        Text(text = outputUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")

                    }

                    DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                        DropdownMenuItem(text = { Text("Centimeters") }, onClick = {oExpanded = false
                            outputUnit = "centimeters, "
                            oConversionFactor.value = 0.01
                            convertUnits()
                        })
                        DropdownMenuItem(text = { Text("Meters") }, onClick = { oExpanded = false
                            outputUnit = "meters"
                            oConversionFactor.value = 1.0
                            convertUnits()
                        })


                        DropdownMenuItem(text = { Text("Feet") }, onClick = { oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        })
                        DropdownMenuItem(text = { Text("Milimeters") }, onClick = {oExpanded = false
                            outputUnit = "Milimeters"
                            oConversionFactor.value = 0.01
                            convertUnits()
                        })
                    }
                Spacer(modifier = Modifier.width(16.dp))
                Text("Result: $outputValue $outputUnit",
                    style = MaterialTheme.typography.headlineMedium)
        }

    }



@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}