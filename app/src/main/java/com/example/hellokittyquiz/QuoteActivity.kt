package com.example.hellokittyquiz

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.hellokittyquiz.databinding.ActivityQuoteBinding

class QuoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuoteBinding
    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nextButton.setOnClickListener {
            quoteViewModel.moveToNext()
            updateQuote()
        }

        binding.previousButton.setOnClickListener {
            quoteViewModel.moveToPrevious()
            updateQuote()
        }

    }

    private fun updateQuote() {
        val quoteTextResId = quoteViewModel.currentQuoteText
        binding.quoteTextView.setText(quoteTextResId)
    }

    companion object{
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, QuoteActivity::class.java).apply{

            }
        }
    }
}
