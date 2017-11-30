package com.bingo.dailykotlin.mvp.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.bingo.dailykotlin.R
import kotlinx.android.synthetic.main.activity_splash.*
import org.jetbrains.anko.startActivity
import java.util.*

/**
 * @author bingo.
 * @date Create on 2017/11/22.
 * @Description
 */
class SplashActivity : AppCompatActivity() {

    private val ANIMATION_DURATION = 2000L
    private val mSCALE_END = 1.13f
    private val SPLASH_ARRAY = intArrayOf(
            R.drawable.splash0,
            R.drawable.splash1,
            R.drawable.splash2,
            R.drawable.splash3,
            R.drawable.splash4,

            R.drawable.splash6,
            R.drawable.splash7,
            R.drawable.splash8,
            R.drawable.splash9,
            R.drawable.splash10,
            R.drawable.splash11,
            R.drawable.splash12,
            R.drawable.splash13,
            R.drawable.splash14,
            R.drawable.splash15,
            R.drawable.splash16)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        val random = Random(SystemClock.elapsedRealtime())
        iv_splash.setImageResource(SPLASH_ARRAY[random.nextInt(SPLASH_ARRAY.size)])

        val tv = findViewById<TextView>(R.id.splash_app_name)
        tv.setText("Bingo")

        animateImage()
    }

    private fun animateImage() {
        val animatorX = ObjectAnimator.ofFloat(iv_splash, "scaleX", 1f, mSCALE_END)
        val animatorY = ObjectAnimator.ofFloat(iv_splash, "scaleY", 1f, mSCALE_END)
        val animateSet = AnimatorSet()
        animateSet.setDuration(ANIMATION_DURATION).play(animatorX).with(animatorY)
        animateSet.start()

        animateSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                super.onAnimationEnd(animation)
//                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                startActivity<MainActivity>()
                finish()
            }
        })
    }


}