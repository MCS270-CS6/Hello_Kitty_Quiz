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
    public val questionBank = listOf(Question(R.string.question1, true, false),
        Question(R.string.question2, false, false),
        Question(R.string.question3, false, false),
        Question(R.string.question4, false, false),
        Question(R.string.question5, true, false),
        Question(R.string.question6, true, false)
    )
    public var currentIndex = 0
    public var currentQuestionAnswered = questionBank[currentIndex].answered
    public var correct = 0
    public var incorrect = 0

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
        if (currentIndex > 1){
            currentIndex = (currentIndex-1)%questionBank.size
        }
        else{
            currentIndex = questionBank.size - 1
        }
        currentQuestionAnswered = questionBank[currentIndex].answered
    }

    fun setAnswered() {
        questionBank[currentIndex].answered = true
    }
}