package br.com.boxui.ui

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.boxui.R
import br.com.ui.BoxUi

class ComponentActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BoxUi().start(this)
    }

    override fun onBackPressed() {
        supportFragmentManager.backStackEntryCount.takeIf { it > 1 }?.run {
            supportFragmentManager.popBackStackImmediate()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}