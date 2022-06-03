package com.example.chinesedictionary.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.chinesedictionary.R
import com.example.chinesedictionary.databinding.ActivityFlashcardBinding

class FragmentFlashcardReveal : Fragment(R.layout.fragment_flashcard_reveal) {
    private var _binding: ActivityFlashcardBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bundle = arguments

        if (bundle != null) {
            val character = bundle.getString("intent_character", "eueueu")
            val pinyin = bundle.getString("intent_pinyin", "")
            val translation = bundle.getString("intent_translation", "")

            val returnView: View = inflater.inflate(R.layout.fragment_flashcard_reveal, container, false)
            val txtOne = returnView.findViewById<View>(R.id.wordCharacter) as TextView
            txtOne.text = character

            val txtTwo = returnView.findViewById<View>(R.id.wordPinyin) as TextView
            txtTwo.text = pinyin

            val txtThree = returnView.findViewById<View>(R.id.wordTranslation) as TextView
            txtThree.text = translation

            _binding = ActivityFlashcardBinding.inflate(inflater, container, false)

            return returnView
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(position: Int) : FragmentFlashcardReveal {
            Bundle().putInt("position", position)
            FragmentFlashcardReveal().arguments = Bundle()

            return FragmentFlashcardReveal()
        }
    }
}