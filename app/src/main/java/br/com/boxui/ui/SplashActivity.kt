package br.com.boxui.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            Toast.makeText(this, "Foi", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, ComponentActivity::class.java))
            finish()
        }, 1000)
    }

}