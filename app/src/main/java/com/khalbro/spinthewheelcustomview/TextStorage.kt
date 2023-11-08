package com.khalbro.spinthewheelcustomview

object TextStorage {

    private val colorSectors =
        arrayOf("GREEN", "BLUE", "INDIGO", "VIOLET", "RED", "ORANGE", "YELLOW")

    fun getResult(degree: Int): String {
        var text = ""
        var factorX = 1
        var factorY = 3
        for (i in 0..6) {
            if (degree >= FACTOR * factorX && degree < FACTOR * factorY) {
                text = colorSectors[i]
            }
            factorX += 2
            factorY += 2
        }
        if (degree >= FACTOR * 73 && degree < 360 || degree >= 0 && degree < FACTOR * 1) {
            textColorSectors()
        }
        return text
    }

    fun textColorSectors(): String {
        return colorSectors[colorSectors.size - 1]
    }
}