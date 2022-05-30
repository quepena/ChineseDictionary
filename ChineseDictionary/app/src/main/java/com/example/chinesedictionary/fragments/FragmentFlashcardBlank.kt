package com.example.chinesedictionary.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.chinesedictionary.R
import com.example.chinesedictionary.databinding.ActivityFlashcardBinding


class FragmentFlashcardBlank : Fragment(R.layout.fragment_flashcard_blank) {
    private var _binding: ActivityFlashcardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val bundle = arguments
//        val a = arguments!!.getString("hello") //Use This or The Below Commented Code

//        val bundle: Bundle
//        val bundle = this.arguments
        Log.i("blankeee", bundle.toString())
        if (bundle != null) {
            Log.i("blank", bundle.getString("intent_character", "eeuue"))
            val character = bundle.getString("intent_character", "eueueu")
//            val pinyin = bundle.getString("intent_pinyin", "")
//            val translation = bundle.getString("intent_translation", "")
            _binding = ActivityFlashcardBinding.inflate(inflater, container, false)
            val view = binding.root
            val textChar = view.findViewById<View>(R.id.wordCharacter) as TextView
            textChar.text = character
//            val textP = view.findViewById<View>(R.id.wordPinyin) as TextView
//            textP.text = pinyin
//            val textTransl = view.findViewById<View>(R.id.wordTranslation) as TextView
//            textTransl.text = translation
//
//            binding?.buttonFlsh?.setOnClickListener {
//                var fr = getFragmentManager()?.beginTransaction()
//                fr?.replace(R.id.fragmentContainerView, FragmentFlashcardReveal())
//                fr?.commit()
//            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}