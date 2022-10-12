package com.example.temperatureconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextInsertTemp = findViewById<EditText>(R.id.editTextInsertTemp)
        val textViewConverted = findViewById<TextView>(R.id.textViewConverted)

        // access the items of the list
        val spinner: Spinner = findViewById(R.id.spinnerModes)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.conversion_modes,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        val insertedTemperature : String  = editTextInsertTemp.text.toString()
        val insertedTemperatureFloat : Float = insertedTemperature.toFloat()
        var temperatureResult = 0.0

        spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
                // An item was selected. You can retrieve the selected item using
                // parent.getItemAtPosition(pos)
//                Toast.makeText(this@MainActivity, parent.getItemAtPosition(pos).toString(),
//                    Toast.LENGTH_SHORT).show()

                if(parent.getItemAtPosition(pos).toString() == "Fahrenheit to Celsius"){

                    temperatureResult = insertedTemperatureFloat - 32 / 1.80

                    textViewConverted.text = temperatureResult.toString()

                    Toast.makeText(this@MainActivity, "F to C",
                        Toast.LENGTH_SHORT).show()

                }

                if(parent.getItemAtPosition(pos).toString() == "Celsius to Fahrenheit"){

                    temperatureResult = (insertedTemperatureFloat * 1.8) / 32

                    textViewConverted.text = insertedTemperatureFloat.toString()

                    Toast.makeText(this@MainActivity, "C to F",
                        Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Another interface callback
            }
        }

    }

}