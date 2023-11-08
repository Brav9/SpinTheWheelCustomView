package com.khalbro.spinthewheelcustomview

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import androidx.appcompat.app.AppCompatActivity
import coil.clear
import coil.load
import com.khalbro.spinthewheelcustomview.databinding.ActivityMainBinding
import kotlin.random.Random

const val FACTOR = 25.71f

class MainActivity : AppCompatActivity() {

    private val imvUrl = "https://placebear.com/200/300"

    private lateinit var binding: ActivityMainBinding
    private var random: Random? = null
    private var degreeOld = 0
    private var degreeNew = 0
    private val textStorage = TextStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvResult
        binding.imvWheel
        random = Random

        selectAction()

        binding.btnReset.setOnClickListener {
            resetResource()
        }
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
                binding.tvResult.text = textStorage.getResult(360 - degreeNew % 360)
                selectAction()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        binding.imvWheel.startAnimation(rotate)
        selectAction()
    }

    private fun selectAction() {
        when (binding.tvResult.text) {
            "GREEN" -> binding.imvDrawable.load(imvUrl) { placeholder(R.drawable.ic_launcher_background) }
            "BLUE" -> "text"
            "INDIGO" -> binding.imvDrawable.load(imvUrl) { placeholder(R.drawable.ic_launcher_background) }
            "VIOLET" -> "text"
            "RED" -> "text"
            "ORANGE" -> binding.imvDrawable.load(imvUrl) { placeholder(R.drawable.ic_launcher_background) }
            "YELLOW" -> "text"
        }
    }

    private fun resetResource() {
        binding.imvDrawable.clear()
    }
}

