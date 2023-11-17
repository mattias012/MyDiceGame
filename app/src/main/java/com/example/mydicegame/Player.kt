package com.example.mydicegame
import java.io.Serializable

class Player(private val playerName: String, private var score: Int, private var tries: Int) : Serializable {


    fun getTries(): Int {
        return this.tries
    }
    fun setTries(){
        this.tries = this.tries - 1
    }
    fun setNewTries(){
        this.tries = 3
    }
    fun setScore(){
        this.score = this.score + 1
    }
    fun getPlayerName(): String{
        return this.playerName
    }
    fun getScore(): Int {
        return this.score
    }
}