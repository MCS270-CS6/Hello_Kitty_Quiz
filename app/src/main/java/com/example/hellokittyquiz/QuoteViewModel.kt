package com.example.hellokittyquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class QuoteViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    val quoteBank = listOf(Quote(R.string.quote1),
        Quote(R.string.quote2),
        Quote(R.string.quote3),
        Quote(R.string.quote4),
        Quote(R.string.quote5)
    )

    var currentIndex = 0
    val currentQuoteText: Int
        get() = quoteBank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex+1) % quoteBank.size
    }

    fun moveToPrevious() {
        if (currentIndex > 1){
            currentIndex = (currentIndex-1)%quoteBank.size
        }
        else{
            currentIndex = quoteBank.size - 1
        }
    }

}