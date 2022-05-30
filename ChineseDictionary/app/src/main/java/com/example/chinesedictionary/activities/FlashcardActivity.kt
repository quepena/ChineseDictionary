package com.example.chinesedictionary.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.chinesedictionary.R
import com.example.chinesedictionary.databinding.ActivityFlashcardBinding
import com.example.chinesedictionary.fragments.FragmentFlashcardBlank
import com.example.chinesedictionary.fragments.FragmentFlashcardReveal


class FlashcardActivity : AppCompatActivity() {
//    lateinit var button: Button
    lateinit var binding: ActivityFlashcardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlashcardBinding.inflate(layoutInflater)

//        val intentId = intent.getStringExtra("intent_id")
        val intentCharacter = intent.getStringExtra("intent_character")


        val intentPinyin = intent.getStringExtra("intent_pinyin")
        val intentTranslation = intent.getStringExtra("intent_translation")

        Log.i("getting", intent.getStringExtra("intent_character").toString())
        val bundle = Bundle()
        bundle.putString("intent_character", intentCharacter)
//        bundle.putString("intent_pinyin", intentPinyin)
//        bundle.putString("intent_translation", intentTranslation)

        val fragmentManager = supportFragmentManager

        val fragmentBlank = FragmentFlashcardBlank()
        fragmentBlank.setArguments(bundle)

        Log.i("bnaakk", fragmentBlank.toString())

        val fragmentReveal = FragmentFlashcardReveal()
        fragmentReveal.setArguments(bundle)

        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainerView, fragmentBlank)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()


//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragmentContainerView2, fragmentBlank)
//        transaction.commit()

        setContentView(binding.root)

        binding?.buttonFlsh?.setOnClickListener {
//            setContentView(R.layout.activity_flashcard_reveal)

            fragmentTransaction.add(R.id.fragmentContainerView2, fragmentReveal)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

//        var click = 0

//        binding.buttonFlsh.setOnClickListener{
//            if(click == 0) {
////                val transaction = supportFragmentManager.beginTransaction()
////                transaction.replace(fragmentBlank, fragmentReveal).commit()
////                transaction.replace(R.id.fragment_layout_id, fragment)
////                setContentView(R.layout.activity_flashcard)
//                click += 1
//            } else {
//                setContentView(R.layout.activity_flashcard_reveal)
//                click = 0
//            }
//        }

//        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            setContentView(R.layout.activity_main_landscape)
//        }
//        else {
//            setContentView(R.layout.activity_main)
//        }
    }
}
