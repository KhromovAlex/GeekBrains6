package com.example.gbapp6.presentation

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.gbapp6.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val splashScreen = installSplashScreen()

        setContentView(R.layout.activity_main)

        splashScreen.setOnExitAnimationListener { splashScreenProvider ->
            ObjectAnimator.ofFloat(
                    splashScreenProvider.view,
                    View.SCALE_X,
                    1f,
                    0f
            ).apply {
                interpolator = AnticipateOvershootInterpolator()
                duration = 300
                doOnEnd {
                    splashScreenProvider.remove()
                }
            }.start()
        }

    }
}
