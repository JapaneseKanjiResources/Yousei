package com.xwilarg.yousei.quizz

import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xwilarg.yousei.DrawingView
import com.xwilarg.yousei.R

class QuizzDrawActivity : QuizzCommon() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hiraganaDrawInfo = Gson().fromJson(this.resources.openRawResource(R.raw.hiragana_draw).bufferedReader().use { it.readText() }, object : TypeToken<KanaDrawInfo>() {}.type)
        katakanaDrawInfo = Gson().fromJson(this.resources.openRawResource(R.raw.katakana_draw).bufferedReader().use { it.readText() }, object : TypeToken<KanaDrawInfo>() {}.type)
        setContentView(R.layout.activity_quizz_draw)
        findViewById<View>(R.id.viewDraw)
        preload()
    }

    fun answer(view: View) {
        val btm = findViewById<DrawingView>(R.id.viewDraw).getContent()
        var pixels = IntArray(btm.width * btm.height)
        btm.getPixels(pixels, 0, btm.width, 0, 0, btm.width, btm.height)
        var tmp = ArrayList<ArrayList<Int>>()
        var i = 0;
        // We put all pixels in a 2D array
        var currTmp = ArrayList<Int>()
        for (p in pixels){
            currTmp.add(if (p == -3487030) {
                0
            } else {
                1
            })
            i++
            if (i == btm.width) {
                i = 0
                tmp.add(currTmp)
                var currTmp = ArrayList<Int>()
            }
        }

        while (tmp.all {it[0] == 0})
            for (i in 0..tmp.size) tmp[0].removeAt(0)

        while (tmp.all {it[tmp.lastIndex] == 0})
            for (i in 0..tmp.size) tmp[tmp.lastIndex].removeAt(0)

        while (tmp[0].all {it == 0})
            for (i in 0..tmp.size) tmp.removeAt(0)

        while (tmp[tmp.lastIndex].all {it == 0})
            for (i in 0..tmp.size) tmp.removeAt(tmp.lastIndex)

        val closestHiragana = getClosest(tmp, hiraganaDrawInfo)
        val closestKatakana = getClosest(tmp, katakanaDrawInfo)
        checkAnswer(if (closestHiragana.second > closestKatakana.second) {
            closestHiragana.first
        } else {
            closestKatakana.first
        })
    }

    fun getClosest(pixels: ArrayList<ArrayList<Int>>, info: ArrayList<KanaDrawInfo>): Pair<String, Int> {
        var bestKana = ""
        var bestScore = -1

        for (kana in info) {
            val width = kana.width
            val height = kana.height
        }
    }

    fun clear(view: View) {
        findViewById<DrawingView>(R.id.viewDraw).clear()
    }

    lateinit var hiraganaDrawInfo: ArrayList<KanaDrawInfo>
    lateinit var katakanaDrawInfo: ArrayList<KanaDrawInfo>
}