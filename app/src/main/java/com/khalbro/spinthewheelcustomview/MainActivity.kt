package com.khalbro.spinthewheelcustomview

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import coil.clear
import coil.load
import com.khalbro.spinthewheelcustomview.databinding.ActivityMainBinding

const val FACTOR = 25.71f

class MainActivity : AppCompatActivity() {

    private val imvUrlOne = "https://loremflickr.com/1080/800"
    private val imvUrlTwo = "https://loremflickr.com/1080/800/tree,moon"
    private val imvUrlTree = "https://loremflickr.com/1080/800/desert,sand,cactus"
    private lateinit var binding: ActivityMainBinding
    private val textStorage = TextStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var startPoint = 0
        var endPoint = 0

        binding.seekBar.setOnSeekBarChangeListener(object  : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val scale:Float = ((progress/100.0f) + 1)
                binding.imvWheel.scaleX = scale
                binding.imvWheel.scaleY = scale
                binding.tvVolume.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
               if (seekBar != null){
                   startPoint =seekBar.progress
               }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null){
                    endPoint =seekBar.progress
                }
            }
        })

        binding.btnReset.setOnClickListener {
            resetResource()
        }
    }

    fun onClickStart(view: View?) {
        val rotate = RotateAnimation(
            textStorage.degreeOld().toFloat(),
            textStorage.degreeNew().toFloat(),
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f,
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.duration = 3600
        rotate.fillAfter = true
        rotate.interpolator = DecelerateInterpolator()
        rotate.setAnimationListener(object : Animation.AnimationListener {

            override fun onAnimationStart(animation: Animation) {
                binding.tvResult.text = ""
            }

            override fun onAnimationEnd(animation: Animation) {
                binding.tvResult.text = textStorage.getResultSector()
                selectAction()
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })

        binding.imvWheel.startAnimation(rotate)
    }

    private fun selectAction() {
        when (binding.tvResult.text) {
            "GREEN" -> {
                binding.imvDrawable.load(imvUrlOne) { placeholder(R.drawable.ic_launcher_foreground) }
                binding.cvDraw.text = ""
            }
            "BLUE" -> {
                binding.imvDrawable.clear()
            }
            "INDIGO" -> {
                binding.imvDrawable.load(imvUrlTree) { placeholder(R.drawable.ic_launcher_foreground) }
                binding.cvDraw.text = ""
            }
            "VIOLET" -> binding.imvDrawable.clear()
            "RED" -> binding.imvDrawable.clear()
            "ORANGE" -> {
                binding.imvDrawable.load(imvUrlTwo) { placeholder(R.drawable.ic_launcher_foreground) }
                binding.cvDraw.text = ""
            }
            "YELLOW" -> binding.imvDrawable.clear()
        }
    }

    private fun resetResource() {
        binding.imvDrawable.clear()
        binding.tvResult.text = ""
        binding.cvDraw.text = ""
    }
}

