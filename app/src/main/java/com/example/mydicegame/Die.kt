package com.example.mydicegame

class Die(private val sides: Int) {
    // En egenskap för att lagra det aktuella värdet på tärningen
    private var faceValue: Int = 1

    // En metod för att rulla tärningen och returnera det nya värdet
    fun roll(): Int {
        faceValue = (1..sides).random()
        return faceValue
    }

    // En metod för att hämta det aktuella värdet på tärningen
    fun getCurrentValue(): Int {
        return faceValue
    }
}