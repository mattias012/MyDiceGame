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
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable
import org.w3c.dom.Text

class ResultViewActivity : AppCompatActivity() {

    var player: Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_view)

        var imageView = findViewById<ImageView>(R.id.imageView)
        //val buttonBack = findViewById<ImageView>(R.id.goBackImageView)
        val resultViewHeadLine = findViewById<TextView>(R.id.resultTextView)
        val retryImageViewButton = findViewById<ImageView>(R.id.retryImageView)

        player = intent.getSerializableExtra("player") as Player

        val localPlayer = player


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
            var triesLeft = intent.getIntExtra("tries", 1)


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

                localPlayer?.setScore()

                guessIsCorrectTextView.text =
                    "Yeay! You guessed correct! Score: ${localPlayer?.getScore()}"
                guessIsCorrectTextView.isVisible = true

            } else {
                //Make sure it is not null
                localPlayer?.setTries()

                if (localPlayer?.getTries() == 0) {

                    Handler(Looper.getMainLooper()).postDelayed({
                        // Kod som ska köras efter fördröjningen
                        imageView.setImageResource(android.R.color.transparent)
                        retryImageViewButton.setImageResource(android.R.color.transparent)
                        resultViewHeadLine.text = "That was your last credit..."
                        retryImageViewButton.setBackgroundResource(R.drawable.playagain)
                        imageView.setBackgroundResource(R.drawable.gameover)
                    }, 3000)
                }

                guessIsCorrectTextView.text =
                    "${localPlayer?.getPlayerName()}, Too bad, wrong guess, no points this time. ${localPlayer?.getTries()} tries left."

            }

            //Go back/try again
            retryImageViewButton.setOnClickListener {

                val data = Intent()
                data.putExtra("player", localPlayer)
                data.putExtra("score", localPlayer?.getScore())
                if (localPlayer?.getTries() == 0) {
                    data.putExtra("retry", "yes")
                }
                setResult(Activity.RESULT_OK, data)
                finish()

            }
        }, 2000)
    }
}