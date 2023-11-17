package com.example.mydicegame

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {

    //Create player on launch
    var player = Player("Donatello", 0, 3)
   // lateinit var gameOverView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //gameOverView = findViewById<ImageView>(R.id.gameOverImageView)
       // gameOverView.isVisible = false

        //Depending on choice
        val buttonOne = findViewById<Button>(R.id.buttonOne)
        val buttonTwo = findViewById<Button>(R.id.buttonTwo)
        val buttonThree = findViewById<Button>(R.id.buttonThree)
        val buttonFour = findViewById<Button>(R.id.buttonFour)
        val buttonFive = findViewById<Button>(R.id.buttonFive)
        val buttonSix = findViewById<Button>(R.id.buttonSix)


        if (player.getTries() == 0) {

            //gameOverView.isVisible = true

            buttonOne.isVisible = false
            buttonTwo.isVisible = false
            buttonThree.isVisible = false
            buttonFour.isVisible = false
            buttonFive.isVisible = false
            buttonSix.isVisible = false
        }
        else {

            buttonOne.setOnClickListener {
                handleButtonClick(1, player.getTries(), player)
            }
            buttonTwo.setOnClickListener {
                handleButtonClick(2, player.getTries(), player)
            }
            buttonThree.setOnClickListener {
                handleButtonClick(3, player.getTries(), player)
            }
            buttonFour.setOnClickListener {
                handleButtonClick(4, player.getTries(), player)
            }
            buttonFive.setOnClickListener {
                handleButtonClick(5, player.getTries(), player)
            }
            buttonSix.setOnClickListener {
                handleButtonClick(6, player.getTries(), player)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            player = data?.getSerializableExtra("player") as Player
            var score = data?.getSerializableExtra("score")
            var retry = data?.getSerializableExtra("retry")

            if (retry == "yes") {
                player.setNewTries()
            }
        }
    }

    private fun handleButtonClick(guessedNumber: Int, tries: Int, player: Player) {

        //Send to next view, send dice value with
        val intent = Intent(this, ResultViewActivity::class.java)
        intent.putExtra("guessedDiceValue", guessedNumber) // send data from dice after roll
        intent.putExtra("player", player) // Also send with list of players


        //Also send number of tries
            intent.putExtra(
                "tries",
                tries
            ) // send data from dice after roll

        // Start next view
        startActivityForResult(intent, 1)
    }
}