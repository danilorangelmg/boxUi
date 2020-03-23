package br.com.boxui.ui

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.boxui.R
import br.com.ui.BoxUi

class ComponentActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BoxUi().start(this)
    }

}