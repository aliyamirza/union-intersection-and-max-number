package com.app.temp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.temp.ui.theme.TempTheme

class MainActivity : ComponentActivity() {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var editText3: EditText
    private lateinit var buttonCalculate: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText1 = findViewById(R.id.edit_text1)
        editText2 = findViewById(R.id.edit_text2)
        editText3 = findViewById(R.id.edit_text3)
        buttonCalculate = findViewById(R.id.button_calculate)
        textViewResult = findViewById(R.id.text_view_result)

        buttonCalculate.setOnClickListener {
            calculate(editText1, editText2, editText3, textViewResult)
        }
    }

    private fun calculate(
        editText1: EditText,
        editText2: EditText,
        editText3: EditText,
        textViewResult: TextView
    ) {
        try {
            val text1 = editText1.text.toString()
            val text2 = editText2.text.toString()
            val text3 = editText3.text.toString()

            if (text1.isEmpty() || text2.isEmpty() || text3.isEmpty()) {
                textViewResult.text = "Please enter values in all fields."
                return
            }

            val list1 = text1.split(",").map { it.trim().toInt() }.toSet()
            val list2 = text2.split(",").map { it.trim().toInt() }.toSet()
            val list3 = text3.split(",").map { it.trim().toInt() }.toSet()

            val intersection = list1.intersect(list2).intersect(list3)
            val union = list1.union(list2).union(list3)
            val max = union.maxOrNull()

            val result = "Intersection: ${intersection.joinToString(", ")}\n" +
                    "Union: ${union.joinToString(", ")}\n" +
                    "Max: $max"

            textViewResult.text = result
        } catch (e: Exception) {
            textViewResult.text = "Error: ${e.message}"
        }
    }
}