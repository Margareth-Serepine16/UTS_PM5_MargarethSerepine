package com.example.utslab5mobile_margareth

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // Declare variables to store answers
    private var soal1Answer: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        val tv_finishbtn = findViewById<Button>(R.id.finishbtn)
        val aSoal1 = findViewById<RadioButton>(R.id.asoal1)
        val bSoal1 = findViewById<RadioButton>(R.id.bsoal1)
        val cSoal1 = findViewById<RadioButton>(R.id.csoal1)
        val dSoal1 = findViewById<RadioButton>(R.id.dsoal1)

        val aSoal2 = findViewById<RadioButton>(R.id.asoal2)
        val bSoal2 = findViewById<RadioButton>(R.id.bsoal2)
        val cSoal2 = findViewById<RadioButton>(R.id.csoal2)
        val dSoal2 = findViewById<RadioButton>(R.id.dsoal2)

        val aSoal3 = findViewById<RadioButton>(R.id.asoal3)
        val bSoal3 = findViewById<RadioButton>(R.id.bsoal3)
        val cSoal3 = findViewById<RadioButton>(R.id.csoal3)
        val dSoal3 = findViewById<RadioButton>(R.id.dsoal3)

        val aSoal4 = findViewById<RadioButton>(R.id.asoal4)
        val bSoal4 = findViewById<RadioButton>(R.id.bsoal4)
        val cSoal4 = findViewById<RadioButton>(R.id.csoal4)
        val dSoal4 = findViewById<RadioButton>(R.id.dsoal4)

        val aSoal5 = findViewById<RadioButton>(R.id.asoal5)
        val bSoal5 = findViewById<RadioButton>(R.id.bsoal5)
        val cSoal5 = findViewById<RadioButton>(R.id.csoal5)
        val dSoal5 = findViewById<RadioButton>(R.id.dsoal5)

        // Set up click listener for answer buttons
        setupAnswerClickListener(aSoal1, bSoal1, cSoal1, dSoal1, correctAnswerId = R.id.asoal1)
        setupAnswerClickListener(aSoal2, bSoal2, cSoal2, dSoal2, correctAnswerId = R.id.bsoal2)
        setupAnswerClickListener(aSoal3, bSoal3, cSoal3, dSoal3, correctAnswerId = R.id.csoal3)
        setupAnswerClickListener(aSoal4, bSoal4, cSoal4, dSoal4, correctAnswerId = R.id.dsoal4)
        setupAnswerClickListener(aSoal5, bSoal5, cSoal5, dSoal5, correctAnswerId = R.id.csoal5)

        // Set up click listener for the finish button
        tv_finishbtn.setOnClickListener {
            // Check if all questions are answered
            if (isQuestionAnswered(aSoal1, bSoal1, cSoal1, dSoal1) &&
                isQuestionAnswered(aSoal2, bSoal2, cSoal2, dSoal2) &&
                isQuestionAnswered(aSoal3, bSoal3, cSoal3, dSoal3) &&
                isQuestionAnswered(aSoal4, bSoal4, cSoal4, dSoal4) &&
                isQuestionAnswered(aSoal5, bSoal5, cSoal5, dSoal5)) {
                // Navigate to the result activity
                startActivity(Intent(this, Result::class.java))
                finish()
            } else {
                Toast.makeText(this, "Please answer all questions.", Toast.LENGTH_SHORT).show()
            }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    // Helper function to set up click listeners for answer buttons
    private fun setupAnswerClickListener(vararg radioButtons: RadioButton, correctAnswerId: Int = -1) {
        radioButtons.forEach { radioButton ->
            radioButton.setOnClickListener {
                if (radioButton.id == correctAnswerId) {
                    radioButton.background = ContextCompat.getDrawable(this, R.drawable.button_border_green)
                    if (radioButton.id == R.id.asoal1) {
                        soal1Answer = true
                    }
                } else {
                    radioButton.background = ContextCompat.getDrawable(this, R.drawable.button_border_red)
                }

                // Disable all other answer buttons for the same question
                radioButtons.forEach { otherRadioButton ->
                    if (otherRadioButton !== radioButton) {
                        otherRadioButton.isEnabled = false
                    }
                }
            }
        }
    }

    // Helper function to check if a question is answered
    private fun isQuestionAnswered(vararg radioButtons: RadioButton): Boolean {
        return radioButtons.any { it.isChecked }
    }

}