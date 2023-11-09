package com.khalbro.spinthewheelcustomview

import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

object TextStorage {

    private var degreeOld = 0
    private var degreeNew = 0
    private var text = ""
    private var random = Random
    var currentTextColorLiveData = MutableLiveData<String>()
    private val colorSectors =
        arrayOf("GREEN", "BLUE", "INDIGO", "VIOLET", "RED", "ORANGE", "YELLOW")

    private fun getResult(degree: Int): String {
        currentTextColorLiveData.value = text
        var factorX = 1
        var factorY = 3
        for (i in 0..6) {
            if (degree >= FACTOR * factorX && degree < FACTOR * factorY) {
                text = colorSectors[i]
                currentTextColorLiveData.value = text
            }
            factorX += 2
            factorY += 2
        }
        if (degree >= FACTOR * 73 && degree < 360 || degree >= 0 && degree < FACTOR * 1) {
            textColorSectors()
        }
        return text
    }

    private fun textColorSectors(): String {
        val currentTextColor = colorSectors[colorSectors.size - 1]
        currentTextColorLiveData.value = currentTextColor
        return currentTextColor
    }

    fun degreeOld(): Int {
        degreeOld = degreeNew % 360
        return degreeOld
    }

    fun degreeNew(): Int {
        degreeNew = random.nextInt(3600) + 720
        return degreeNew
    }

    fun getResultSector(): String {
        val currentTextColor = getResult(360 - degreeNew % 360)
        currentTextColorLiveData.value = currentTextColor
        return currentTextColor
    }
}