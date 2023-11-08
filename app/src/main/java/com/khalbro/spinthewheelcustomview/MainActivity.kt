package com.khalbro.spinthewheelcustomview

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import com.khalbro.spinthewheelcustomview.databinding.ActivityMainBinding
import kotlin.random.Random

const val FACTOR = 25.71f

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var random: Random? = null
    private var degreeOld = 0
    private var degreeNew = 0
    private val colorSectors = arrayOf("GREEN", "BLUE", "INDIGO", "VIOLET", "RED", "ORANGE", "YELLOW")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvResult
        binding.imvWheel
        random = Random
    }

    fun onClickStart(view: View?) {
        degreeOld = degreeNew % 360
        degreeNew = random!!.nextInt(3600) + 720
        val rotate = RotateAnimation(
            degreeOld.toFloat(), degreeNew.toFloat(), RotateAnimation.RELATIVE_TO_SELF,
            0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )
        rotate.duration = 3600
        rotate.fillAfter = true
        rotate.interpolator = DecelerateInterpolator()
        rotate.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {
                binding.tvResult.text = ""
            }

            override fun onAnimationEnd(animation: Animation) {
                binding.tvResult.text = getResult(360 - degreeNew % 360)
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        binding.imvWheel.startAnimation(rotate)
    }

    private fun getResult(degree: Int): String {
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
        if (degree >= FACTOR * 73 && degree < 360 || degree >= 0 && degree < FACTOR * 1) text =
            colorSectors[colorSectors.size - 1]
        return text
    }
}

