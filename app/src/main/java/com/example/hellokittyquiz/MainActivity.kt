package com.example.hellokittyquiz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.hellokittyquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels

private const val TAG = "MainActivity";

//private var currentIndex = 0
//private lateinit var true_button:Button
//private lateinit var false_button: Button

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val quizViewModel: QuizViewModel by viewModels()
    private val cheatLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        //handle whatever the result is
        if (result.resultCode == Activity.RESULT_OK) {
            quizViewModel.isCheater =
                result.data?.getBooleanExtra(EXTRA_ANSWER_IS_SHOWN, false) ?: false
        }
    }
    private val quoteLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->

    }

    private var correct = 0
    private var incorrect = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "I dont remember what is supposed to go here")
        // hook up the button to its id
        //true_button = findViewById(R.id.true_button)
        //false_button = findViewById(R.id.false_button)

        // what happen if you click on those buttons
        binding.trueButton.setOnClickListener { view: View ->
            // Do something if you click on true button
            // have a correct toast that pops up
            checkAnswer(true, view)
        }

        binding.falseButton.setOnClickListener { view: View ->
            checkAnswer(false, view)
        }

        // onset listener for the next button
        // ie what happen if you press the next button
        binding.nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        binding.previousButton.setOnClickListener {
            quizViewModel.moveToPrevious()
            updateQuestion()
        }

        binding.cheatButton.setOnClickListener{
            // Start cheat activity, something's gonna happen
            //val intent = Intent(this, CheatActivity::class.java)
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            //startActivity(intent)
            cheatLauncher.launch(intent)
        }

        binding.questionTextView.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        // this will get you the id for the current question in the question bank
        updateQuestion()

        binding.quoteButton.setOnClickListener{
            val intent = QuoteActivity.newIntent(this@MainActivity)
            quoteLauncher.launch(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() is called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume is called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause is called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop is called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy is called")
    }

    private fun updateQuestion(){
        val questionTextResId = quizViewModel.currentQuestionText
        binding.questionTextView.setText(questionTextResId)
        if (quizViewModel.currentQuestionAnswered == true) {
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
        }
        else{
            binding.trueButton.isEnabled = true
            binding.falseButton.isEnabled = true
        }
    }

    private fun checkAnswer(userAnswer:Boolean, view: View){

        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = when {
            quizViewModel.isCheater -> R.string.cheater_string
            userAnswer == correctAnswer -> R.string.correct_string
            else -> R.string.incorrect_string
        }

        if (userAnswer == correctAnswer) {
            correct += 1
            quizViewModel.correct += 1
        }
        else {
            incorrect += 1
            quizViewModel.incorrect += 1
        }

        quizViewModel.setAnswered()
        binding.trueButton.isEnabled = false
        binding.falseButton.isEnabled = false

        var percent_score = 0
        if (quizViewModel.questionBank.size == correct + incorrect) {
            val scoreText = "Score: " + ((correct * 100)/quizViewModel.questionBank.size) + "%"
            Toast.makeText(this, scoreText, Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
        }
        //Snackbar.make(view, messageResId, Snackbar.LENGTH_SHORT).show()
    }



    /* private fun checkPercent(){
        var percent_correct = 0
        if (quizViewModel.currentIndex == correct + incorrect){
            percent_correct = (correct * 100) / quizViewModel.questionBankSize
            Toast.makeText(this,
                this.getString(R.string.score_toast, percent_correct),
                Toast.LENGTH_LONG).show()
        }
    }
     */
}