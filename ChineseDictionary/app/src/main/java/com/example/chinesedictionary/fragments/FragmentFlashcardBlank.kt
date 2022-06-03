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

class FragmentFlashcardBlank : Fragment(R.layout.fragment_flashcard_blank) {
    private var _binding: ActivityFlashcardBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bundle = arguments

        if (bundle != null) {
            val character = bundle.getString("intent_character", "eueueu")

            val returnView: View = inflater.inflate(R.layout.fragment_flashcard_blank, container, false)
            val txtOne = returnView.findViewById<View>(R.id.wordCharacter) as TextView
            txtOne.text = character
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

        fun newInstance(position: Int) : FragmentFlashcardBlank {
            Bundle().putInt("position", position)
            FragmentFlashcardBlank().arguments = Bundle()

            return FragmentFlashcardBlank()
        }
    }
}