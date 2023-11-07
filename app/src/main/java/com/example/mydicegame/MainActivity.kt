package com.example.mydicegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Depending on choice
        val buttonOne = findViewById<Button>(R.id.buttonOne)
        val buttonTwo = findViewById<Button>(R.id.buttonTwo)
        val buttonThree = findViewById<Button>(R.id.buttonThree)
        val buttonFour = findViewById<Button>(R.id.buttonFour)
        val buttonFive = findViewById<Button>(R.id.buttonFive)
        val buttonSix = findViewById<Button>(R.id.buttonSix)



        buttonOne.setOnClickListener {
            handleButtonClick(1)
        }
        buttonTwo.setOnClickListener {
            handleButtonClick(2)
        }
        buttonThree.setOnClickListener {
            handleButtonClick(3)
        }
        buttonFour.setOnClickListener{
            handleButtonClick(4)
        }
        buttonFive.setOnClickListener{
            handleButtonClick(5)
        }
        buttonSix.setOnClickListener{
            handleButtonClick(6)
        }
    }

    private fun handleButtonClick(sides: Int) {

        //Create Dice
        val die = Die(sides)

        //Roll Dice
        die.roll()

        //Send to next view, send dice value with
        val intent = Intent(this, ResultViewActivity::class.java)
        intent.putExtra("dicevalue", die.getCurrentValue()) // send data from dice after roll

        // Start next view
        startActivity(intent)
    }
}