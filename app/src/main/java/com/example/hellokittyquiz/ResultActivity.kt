package com.example.hellokittyquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.hellokittyquiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private val quizViewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var correct_score = quizViewModel.score
        val correct_score10 = Integer.toString(correct_score)
        val correctText= StringBuilder().append(getString(R.string.correctResult_string)).append(" ").append(correct_score10).toString()
        val incorrectText = R.string.incorrectResult_string + quizViewModel.wrong

        binding.correctText.setText(correctText)
        binding.incorrectText.setText(incorrectText)
    }

    companion object{
        fun newIntent(packageContext: Context): Intent {
            return Intent(packageContext, ResultActivity::class.java).apply{

            }
        }
    }
}
