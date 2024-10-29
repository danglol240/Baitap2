package com.example.bai2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextInput: EditText
    private lateinit var radioButtonEven: RadioButton
    private lateinit var radioButtonOdd: RadioButton
    private lateinit var radioButtonSquare: RadioButton
    private lateinit var buttonShow: Button
    private lateinit var textViewResult: TextView
    private lateinit var listViewItems: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextInput = findViewById(R.id.editTextInput)
        radioButtonEven = findViewById(R.id.radioButton1)
        radioButtonOdd = findViewById(R.id.radioButton2)
        radioButtonSquare = findViewById(R.id.radioButton3)
        buttonShow = findViewById(R.id.buttonShow)
        textViewResult = findViewById(R.id.textViewResult)
        listViewItems = findViewById(R.id.listViewItems)

        buttonShow.setOnClickListener {
            val inputText = editTextInput.text.toString()
            if (inputText.isEmpty()) {
                textViewResult.text = "Hãy điền n"
                listViewItems.adapter = null
                return@setOnClickListener
            }

            val n = inputText.toIntOrNull()
            if (n == null || n < 0) {
                textViewResult.text = "Hãy điền n hợp lệ"
                listViewItems.adapter = null
                return@setOnClickListener
            }

            val resultList = when {
                radioButtonEven.isChecked -> getEvenNumbers(n)
                radioButtonOdd.isChecked -> getOddNumbers(n)
                radioButtonSquare.isChecked -> getPerfectSquares(n)
                else -> {
                    textViewResult.text = "Lựa chọn loại số"
                    listViewItems.adapter = null
                    return@setOnClickListener
                }
            }

            textViewResult.text = "Kết quả là n = $n"
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
            listViewItems.adapter = adapter
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getPerfectSquares(n: Int): List<Int> {
        val result = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            result.add(i * i)
            i++
        }
        return result
    }
}

