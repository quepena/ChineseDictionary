package com.example.chinesedictionary.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.chinesedictionary.R
import com.example.chinesedictionary.databinding.ActivityFlashcardBinding
import com.example.chinesedictionary.fragments.FragmentFlashcardBlank
import com.example.chinesedictionary.fragments.FragmentFlashcardReveal

class FlashcardActivity : AppCompatActivity() {
    lateinit var binding: ActivityFlashcardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFlashcardBinding.inflate(layoutInflater)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

        val intentCharacter = intent.getStringExtra("intent_character")
        val intentPinyin = intent.getStringExtra("intent_pinyin")
        val intentTranslation = intent.getStringExtra("intent_translation")

        val ft = supportFragmentManager.beginTransaction()

        val frg = FragmentFlashcardBlank()
        val frg2 = FragmentFlashcardReveal()

        val bdl = Bundle()
        bdl.putString("intent_character", intentCharacter)
        bdl.putString("intent_pinyin", intentPinyin)
        bdl.putString("intent_translation", intentTranslation)
        frg.setArguments(bdl)
        frg2.setArguments(bdl)

        ft.replace(R.id.fragmentContainerView, frg)
        ft.addToBackStack(null)
        ft.commit()

        supportActionBar!!.title = intentCharacter

        setContentView(binding.root)

        binding.buttonFlsh.setOnClickListener {
            val ft = supportFragmentManager.beginTransaction()
            ft.replace(R.id.fragmentContainerView, frg2)
            ft.addToBackStack(null)
            ft.commit()
        }
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return true
    }
}
