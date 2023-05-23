package com.example.retapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.retapp.login.LoginActivity

class SplashActivity : AppCompatActivity() {

    // AndroidManifest -> arranca primero esta pantalla

    private lateinit var ivSplash: ImageView
    private lateinit var tvSplash: TextView
    private lateinit var animation: Animation



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // quitar la actionBar si existe
        supportActionBar?.hide()

        ivSplash = findViewById(R.id.ivSplash)
        tvSplash = findViewById(R.id.tvSplash)

        animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        // escuchador de la animación
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()

            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })


    }


    override fun onStart() {
        super.onStart()
        // iniciador de animacion al arrancar pantalla
        ivSplash.startAnimation(animation)
        tvSplash.startAnimation(animation)
    }
}