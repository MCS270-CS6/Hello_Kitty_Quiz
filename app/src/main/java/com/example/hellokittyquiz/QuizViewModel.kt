package com.example.hellokittyquiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
private const val TAG = "QuizViewModel"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    init {
        Log.d(TAG, "ViewModel instance is created")
    }
    private val questionBank = listOf(Question(R.string.question1, true, false),
        Question(R.string.question2, false, false),
        Question(R.string.question3, false, false),
        Question(R.string.question4, false, false),
        Question(R.string.question5, true, false)
    )
    public var currentIndex = 0
    public var currentQuestionAnswered = questionBank[currentIndex].answered

    val currentQuestionAnswer: Boolean
    get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
    get() = questionBank[currentIndex].textResId

    var isCheater: Boolean
        get() = savedStateHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStateHandle.set(IS_CHEATER_KEY, value)

    fun moveToNext(){
        currentIndex = (currentIndex+1) % questionBank.size
        currentQuestionAnswered = questionBank[currentIndex].answered
    }

    fun moveToPrevious() {
        currentIndex = (currentIndex-1)%questionBank.size
        currentQuestionAnswered = questionBank[currentIndex].answered
    }

    fun setAnswered() {
        questionBank[currentIndex].answered = true
    }
}