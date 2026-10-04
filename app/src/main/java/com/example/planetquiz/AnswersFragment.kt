package com.example.planetquiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class AnswersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_answers, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<TextView>(R.id.answer_text).text =
            requireArguments().getString(ARG_MESSAGE)
    }

    companion object {
        private const val ARG_MESSAGE = "message"

        fun newInstance(message: String) = AnswersFragment().apply {
            arguments = Bundle().apply { putString(ARG_MESSAGE, message) }
        }
    }
}