package com.xwilarg.yousei;

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.gson.Gson
import kotlin.random.Random

open class QuizzCommon : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizz_choices)
    }

    fun preload() {
        val content = this.resources.openRawResource(R.raw.jlpt5).bufferedReader().use { it.readText() }
        kanjis = Gson().fromJson(content, Array<KanjiInfo>::class.java)
        loadQuestion()
    }

    open fun checkAnswer(myAnswer: String) {
        findViewById<TextView>(R.id.textLastKanji).text = currentKanji.kanji
        findViewById<TextView>(R.id.textAnswerYouTitle).text = "Your answer"
        findViewById<TextView>(R.id.textAnswerHimTitle).text = "Right answer"
        findViewById<TextView>(R.id.textAnswerYou).text = myAnswer

        // Check if the answer is correct, wrong or partially correct
        var isCorrect = false
        var closestAnswer : String? = null
        if (!myAnswer.isNullOrBlank()) {
            for (m in currentKanji.meaning) {
                if (myAnswer == m) {
                    closestAnswer = m
                    isCorrect = true
                    break
                }
                if (closestAnswer == null && (myAnswer.contains(m) || m.contains(myAnswer))) {
                    closestAnswer = m
                }
            }
        }

        findViewById<ConstraintLayout>(R.id.ConstraintLayoutAnswer).setBackgroundColor(if (isCorrect) {
            Color.rgb(200, 255, 200)
        } else if (closestAnswer != null) {
            Color.rgb(255, 255, 200)
        } else {
            Color.rgb(255, 200, 200)
        })
        findViewById<TextView>(R.id.textAnswerHim).text = closestAnswer ?: currentKanji.meaning[0]
        loadQuestion()
    }

    fun loadQuestion() {
        currentKanji = kanjis[Random.nextInt(0, kanjis.size)]
        findViewById<TextView>(R.id.textQuizz).text = currentKanji.kanji
        findViewById<TextView>(R.id.textQuizzHelp).text = if (currentKanji.kunyomi.isEmpty()) {
            currentKanji.onyomi?.get(0)
        } else {
            currentKanji.kunyomi?.get(0)
        }
        loadQuestionAfter()
    }

    open fun loadQuestionAfter() { }

    lateinit var kanjis: Array<KanjiInfo>
    lateinit var currentKanji: KanjiInfo
}