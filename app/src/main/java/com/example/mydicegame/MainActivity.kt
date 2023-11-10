package com.example.mydicegame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Create player on launch
        val player = Player("Donatello", 0, 0)

        //List of consecutive guesses
        val listOfPlayers = ArrayList<Player>()

        listOfPlayers.add(player)


        //Depending on choice
        val buttonOne = findViewById<Button>(R.id.buttonOne)
        val buttonTwo = findViewById<Button>(R.id.buttonTwo)
        val buttonThree = findViewById<Button>(R.id.buttonThree)
        val buttonFour = findViewById<Button>(R.id.buttonFour)
        val buttonFive = findViewById<Button>(R.id.buttonFive)
        val buttonSix = findViewById<Button>(R.id.buttonSix)

        buttonOne.setOnClickListener {
            handleButtonClick(1, player.getTries(), listOfPlayers)
        }
        buttonTwo.setOnClickListener {
            handleButtonClick(2, player.getTries(), listOfPlayers)
        }
        buttonThree.setOnClickListener {
            handleButtonClick(3, player.getTries(), listOfPlayers)
        }
        buttonFour.setOnClickListener{
            handleButtonClick(4, player.getTries(), listOfPlayers)
        }
        buttonFive.setOnClickListener{
            handleButtonClick(5, player.getTries(), listOfPlayers)
        }
        buttonSix.setOnClickListener{
            handleButtonClick(6, player.getTries(), listOfPlayers)
        }
    }

    private fun handleButtonClick(guessedNumber: Int, tries: Int, listOfPlayers: ArrayList<Player>) {

        //Send to next view, send dice value with
        val intent = Intent(this, ResultViewActivity::class.java)
        intent.putExtra("guessedDiceValue", guessedNumber) // send data from dice after roll
        intent.putExtra("listOfPlayers", listOfPlayers) // Also send with list of players

        //Also send number of tries
        if (tries >= 3) {
        }
        else {
            intent.putExtra(
                "tries",
                tries
            ) // send data from dice after roll
        }
        // Start next view
        startActivity(intent)
    }
}