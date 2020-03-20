package br.com.boxui.ui

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.ui.BoxUi

class SplashActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            Toast.makeText(this, "Foi", Toast.LENGTH_LONG).show()
            BoxUi().start(this)
        }, 1000)
    }

}