package com.saproduction.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var displayTv: TextView

    var isOperator = false
    var isNumber = false
    var isDot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayTv = findViewById(R.id.displayTv)

    }

    fun calculate(view: View) {

        if(isNumber) {

            isOperator = false

            var split: List<String>
            var prefix = ""
            var tvValue = displayTv.text

            if(tvValue.startsWith("-")) {
                prefix = "-"
                tvValue = tvValue.substring(1)
            }

            if(tvValue.contains("-")) {

                split = tvValue.split("-")
                var first = split.get(0).toDouble()
                var second = split.get(1).toDouble()

                    if(prefix == "-") {
                        first = -first
                    }
                    displayTv.text = "${first - second}"
//                }

            }else if(tvValue.contains("+")) {

                split = tvValue.split("+")
                var first = split.get(0).toDouble()
                var second = split.get(1).toDouble()

                if(prefix == "-") {
                    first = -first
                }

                displayTv.text = "${first + second}"
            }else if(tvValue.contains("*")) {
                split = tvValue.split("*")
                var first = split.get(0).toDouble()
                var second = split.get(1).toDouble()

                if(prefix == "-") {
                    first = -first
                }

                displayTv.text = "${first * second}"
            }else if(tvValue.contains("/")) {
                split = tvValue.split("/")
                var first = split.get(0).toDouble()
                var second = split.get(1).toDouble()

                if(prefix == "-") {
                    first = -first
                }

                displayTv.text = "${first / second}"
            } else {
                displayTv.text = displayTv.text
            }

            // to remove .00 stuff from the text
            var num1 = displayTv.text.toString().toDouble()
            var num2 = num1.toInt()
            if(num1 - num2 > 0) {
                isDot = true
            }else {
                isDot = false
                displayTv.text = num2.toString()
            }


        }

    }

    fun setNumber(view: View) {

        addText(view)
        isNumber = true

    }

    fun clearScreen(view: View) {
        displayTv.text = ""
        isOperator = false
        isNumber = false
        isDot = false
    }

    fun putOperation(view: View) {

        if(!isOperator) {
            isOperator = true;
            if(displayTv.text == "") {
                displayTv.setText("0${(view as Button).text}")
            }else {
                addText(view)
            }
            isNumber = false
            isDot = false
        }

    }

    fun putDot(view: View) {

        if(!isDot) {
            isDot = true
            addText(view)
        }

    }

    private fun addText(view: View) {
        displayTv.setText("${displayTv.text}${(view as Button).text}")
    }

}