package com.example.mydicegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.gif.GifDrawable

class ResultViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_view)

        var imageView = findViewById<ImageView>(R.id.imageView)
        val buttonBack = findViewById<Button>(R.id.buttonGoBack)

        // Exempel: Ladda en bild från res/drawable och visa den i en ImageView
        val imageResource = R.drawable.dice
        Glide.with(this)
            .load(imageResource)
            .into(imageView)

        Handler(Looper.getMainLooper()).postDelayed({
            // Kod som ska köras efter fördröjningen
            imageView.setImageResource(android.R.color.transparent)


        var receivedValue = intent.getIntExtra("dicevalue", 1)

        when (receivedValue) {
            1 -> imageView.setBackgroundResource(R.drawable.dice1)
            2 -> imageView.setBackgroundResource(R.drawable.dice2)
            3 -> imageView.setBackgroundResource(R.drawable.dice3)
            4 -> imageView.setBackgroundResource(R.drawable.dice4)
            5 -> imageView.setBackgroundResource(R.drawable.dice5)
            6 -> imageView.setBackgroundResource(R.drawable.dice6)
            // Lägg till fler fall för andra tärningsvärden om så behövs
            else -> imageView.setBackgroundResource(R.drawable.shrug)
        }

        buttonBack.setOnClickListener {
            finish() // close current view

        }

        }, 3000)
    }
}