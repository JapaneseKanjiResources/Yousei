package com.xwilarg.yousei.quizz

import android.os.Bundle
import android.view.View
import com.xwilarg.yousei.R

class QuizzDrawActivity : QuizzCommon() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_draw)
        findViewById<View>(R.id.viewDraw)
        var drawView = findViewById<View>(R.id.viewDraw)
        preload()
    }

    fun answer(view: View) {
    }
}