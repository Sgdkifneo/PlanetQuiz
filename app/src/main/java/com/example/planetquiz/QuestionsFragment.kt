package com.example.planetquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

class QuestionsFragment : Fragment() {

    // Positions in the planets list: JUPITER=4, SATURN=5, URANUS=6
    private val correctAnswers = intArrayOf(4, 5, 6)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_questions, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val title = view.findViewById<TextView>(R.id.title)
        val questionList = view.findViewById<LinearLayout>(R.id.question_list)
        val planetList = view.findViewById<LinearLayout>(R.id.planet_list)
        val backButton = view.findViewById<Button>(R.id.btn_back)

        val questions = resources.getStringArray(R.array.questions)
        val planets = resources.getStringArray(R.array.planets)
        val details = resources.getStringArray(R.array.answer_details)

        var currentQuestion = 0

        // Build the 8 planet buttons
        planets.forEachIndexed { planetIndex, name ->
            val button = Button(requireContext())
            button.text = name
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.topMargin = 12
            button.layoutParams = params

            button.setOnClickListener {
                val message = if (planetIndex == correctAnswers[currentQuestion]) {
                    getString(R.string.correct_prefix) + details[currentQuestion]
                } else {
                    getString(R.string.wrong)
                }
                childFragmentManager.beginTransaction()
                    .replace(R.id.answer_container, AnswersFragment.newInstance(message))
                    .commit()
            }
            planetList.addView(button)
        }

        // Question buttons
        val questionButtons = listOf(R.id.btn_q1, R.id.btn_q2, R.id.btn_q3)
        questionButtons.forEachIndexed { index, id ->
            view.findViewById<Button>(id).setOnClickListener {
                currentQuestion = index
                title.text = questions[index]
                questionList.visibility = View.GONE
                planetList.visibility = View.VISIBLE
                backButton.visibility = View.VISIBLE
            }
        }

        // Back to the questions
        backButton.setOnClickListener {
            title.text = getString(R.string.quiz_title)
            questionList.visibility = View.VISIBLE
            planetList.visibility = View.GONE
            backButton.visibility = View.GONE
            childFragmentManager.findFragmentById(R.id.answer_container)?.let {
                childFragmentManager.beginTransaction().remove(it).commit()
            }
        }
    }
}