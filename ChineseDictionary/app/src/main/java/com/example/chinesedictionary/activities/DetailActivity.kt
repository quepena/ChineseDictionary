package com.example.chinesedictionary.activities

import android.R
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chinesedictionary.adapters.ExampleAdapter
import com.example.chinesedictionary.databinding.ActivityDetailBinding
import com.example.chinesedictionary.models.ExampleModel
import com.example.chinesedictionary.models.WordModel
import com.example.chinesedictionary.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailActivity : AppCompatActivity() {
    private val TAG: String = "DetailActivity"

    private lateinit var binding : ActivityDetailBinding

    private lateinit var exampleAdapter: ExampleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var intentTitle = intent.getStringExtra("intent_title")
        val intentId = intent.getStringExtra("intent_id")
        supportActionBar!!.title = intentTitle
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        setupRecyclerView()
        getDataFromApi(intentId.toString())
    }

    private fun setupRecyclerView(){
        exampleAdapter = ExampleAdapter(arrayListOf())

        binding.recyclerView.apply {
            binding.recyclerView.layoutManager = LinearLayoutManager(this@DetailActivity)
            binding.recyclerView.adapter = exampleAdapter
        }
    }

    private fun getDataFromApi(intentId: String){
        showLoading(true)

        if(intentId != "") {
            ApiService.endpoint.dataWord(intentId)
                .enqueue(object : Callback<WordModel> {
                    override fun onFailure(call: Call<WordModel>, t: Throwable) {
                        printLog(t.toString())
                        showLoading(false)
                    }

                    override fun onResponse(
                        call: Call<WordModel>,
                        response: Response<WordModel>
                    ) {
                        showLoading(false)
                        if (response.isSuccessful) {
                            showWordResult(response.body()!!)
                        }
                    }
                })

            ApiService.endpoint.dataExamples(intentId)
                .enqueue(object : Callback<ExampleModel> {
                    override fun onFailure(call: Call<ExampleModel>, t: Throwable) {
                        printLog(t.toString())
                        showLoading(false)
                    }

                    override fun onResponse(
                        call: Call<ExampleModel>,
                        response: Response<ExampleModel>
                    ) {
                        showLoading(false)
                        if (response.isSuccessful) {
                            showExampleResult(response.body()!!)
                        }
                    }
                })
        }
    }

    private fun printLog(message: String) {
        Log.d(TAG, message)
    }

    private fun showLoading(loading: Boolean) {
        when(loading) {
            true -> binding.progressBar.visibility = View.VISIBLE
            false -> binding.progressBar.visibility = View.GONE
        }
    }

    private fun showWordResult(results: WordModel) {
        binding.wordCharacter.text = results.result.character
        binding.wordPinyin.text = results.result.pinyin
        binding.wordTranslation.text = results.result.translation
    }

    private fun showExampleResult(results: ExampleModel) {
        for (result in results.result) printLog( "title: ${result.characters}" )
        exampleAdapter.setData( results.result )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.home -> {
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