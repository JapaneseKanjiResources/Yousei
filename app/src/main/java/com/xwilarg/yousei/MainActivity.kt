package com.xwilarg.yousei

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    
    fun startGameFree(view: View) {
        startActivity(Intent(this, QuizzNormalActivity::class.java))
    }

    fun startGameChoices(view: View) {
        startActivity(Intent(this, QuizzChoicesActivity::class.java))
    }
}