package com.example.mydicegame
import java.io.Serializable

class Player(private val playerName: String, private var score: Int = 0, private var tries: Int = 0) : Serializable {


    fun getTries(): Int {
        return this.tries
    }
    fun setTries(){
        this.tries = this.tries + 1
    }
    fun setScore(){
        this.score = this.score + 1
    }
    fun getPlayerName(): String{
        return this.playerName
    }
}