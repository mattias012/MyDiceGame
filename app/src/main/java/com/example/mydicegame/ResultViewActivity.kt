package com.example.mydicegame

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable

class ResultViewActivity : AppCompatActivity() {

    var player: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_view)

        var imageView = findViewById<ImageView>(R.id.imageView)
        val buttonBack = findViewById<Button>(R.id.buttonGoBack)

        player = intent.getSerializableExtra("player") as Player

        val localPlayer = player

        //Make sure it is not null
        localPlayer?.setTries()

        // Exempel: Ladda en bild från res/drawable och visa den i en ImageView
        val imageResource = R.drawable.dice
        Glide.with(this)
            .load(imageResource)
            .into(imageView)

        Handler(Looper.getMainLooper()).postDelayed({
            // Kod som ska köras efter fördröjningen
            imageView.setImageResource(android.R.color.transparent)

            //Create our Dice
            val die = Die(6)

            //Roll Dice
            die.roll()

            var valueOnDiceAfterRoll = die.getCurrentValue()

            var receivedValue = intent.getIntExtra("guessedDiceValue", 1)


            when (valueOnDiceAfterRoll) {
                1 -> imageView.setBackgroundResource(R.drawable.dice1)
                2 -> imageView.setBackgroundResource(R.drawable.dice2)
                3 -> imageView.setBackgroundResource(R.drawable.dice3)
                4 -> imageView.setBackgroundResource(R.drawable.dice4)
                5 -> imageView.setBackgroundResource(R.drawable.dice5)
                6 -> imageView.setBackgroundResource(R.drawable.dice6)
                // Lägg till fler fall för andra tärningsvärden om så behövs
                else -> imageView.setBackgroundResource(R.drawable.shrug)
            }

            var guessIsCorrectTextView = findViewById<TextView>(R.id.guessIsCorrectTextView)


            if (receivedValue == valueOnDiceAfterRoll) {

                guessIsCorrectTextView.text = "Yeay! You guessed correct score"
                guessIsCorrectTextView.isVisible = true

                Log.d("!!!", "Yes correct guess")

            } else {
                if (localPlayer != null) {

                    guessIsCorrectTextView.text = "${localPlayer.getPlayerName()}, Too bad, wrong guess, no points this time. ${localPlayer.getTries()} tries left."
                }
            }

            //Go back/try again
            buttonBack.setOnClickListener {


                val data = Intent()
                data.putExtra("player", player)
                setResult(Activity.RESULT_OK, data)
                finish()


            }

        }, 2000)
    }
}