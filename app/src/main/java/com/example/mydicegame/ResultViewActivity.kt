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
        var receivedValue = intent.getIntExtra("guessedDiceValue", 1)
        resultViewHeadLine.text = "Let's see if we can get a $receivedValue..."

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

            resultViewHeadLine.text = "Result: "

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

                guessIsCorrectTextView.text = "Yeay! You guessed correct! Score: ${localPlayer?.getScore()}"
                guessIsCorrectTextView.isVisible = true

            } else {
                //Make sure it is not null
                localPlayer?.setTries()

                guessIsCorrectTextView.text = "Too bad, wrong guess, no points this time. \n" +
                        "Number of tries left: ${localPlayer?.getTries()}"

                if (localPlayer?.getTries() == 0) {

                    retryImageViewButton.isVisible = false

                    Handler(Looper.getMainLooper()).postDelayed({
                        // Kod som ska köras efter fördröjningen
                        imageView.setImageResource(android.R.color.transparent)
                        retryImageViewButton.setImageResource(android.R.color.transparent)
                        resultViewHeadLine.text = "That was your last credit..."
                        retryImageViewButton.setBackgroundResource(R.drawable.playagain)
                        retryImageViewButton.isVisible = true
                        imageView.setBackgroundResource(R.drawable.gameover)
                        guessIsCorrectTextView.text = "To get 3 new credits, push the reset button below!"
                    }, 2000)
                }



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