package com.example.chinesedictionary.activities

import android.R
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chinesedictionary.ConnectionLiveData
import com.example.chinesedictionary.adapters.WordAdapter
import com.example.chinesedictionary.databinding.ActivityWordsBinding
import com.example.chinesedictionary.models.WordsModel
import com.example.chinesedictionary.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WordsActivity : AppCompatActivity() {
    private val TAG: String = "WordsActivity"

    private lateinit var connectionLiveData: ConnectionLiveData

    private lateinit var binding : ActivityWordsBinding

    private lateinit var wordAdapter: WordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var intentTitle = intent.getStringExtra("intent_title")
        val intentId = intent.getStringExtra("intent_id")
        supportActionBar!!.title = intentTitle
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        var network = isNetworkAvailable(this)

        if (!network) {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
        }

        connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this) { isNetworkAvailable ->
            if(!isNetworkAvailable) {
                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show()
            } else {
                isNetworkAvailable?.let {
                    setupRecyclerView()
                    getDataFromApi(intentId.toString())
                }

            }
        }
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }

    private fun setupRecyclerView(){
        wordAdapter = WordAdapter(arrayListOf(), object : WordAdapter.OnAdapterListener {
            override fun onClick(result: WordsModel.Result) {
                startActivity(
                    Intent(this@WordsActivity, DetailActivity::class.java)
                        .putExtra("intent_title", result.character)
                        .putExtra("intent_id", result._id)
                )
            }
        })

        binding.recyclerView.apply {
            binding.recyclerView.layoutManager = LinearLayoutManager(this@WordsActivity)
            binding.recyclerView.adapter = wordAdapter
        }
    }

    private fun getDataFromApi(intentId: String){
        showLoading(true)

        if(intentId != "") {
            ApiService.endpoint.dataWords(intentId)
                .enqueue(object : Callback<WordsModel> {
                    override fun onFailure(call: Call<WordsModel>, t: Throwable) {
                        printLog(t.toString())
                        showLoading(false)
                    }

                    override fun onResponse(
                        call: Call<WordsModel>,
                        response: Response<WordsModel>
                    ) {
                        showLoading(false)
                        if (response.isSuccessful) {
                            showResult(response.body()!!)
                            Log.i("smth",response.body().toString())
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

    private fun showResult(results: WordsModel) {
        for (result in results.result) printLog( "title: ${result.character}" )
        wordAdapter.setData( results.result )
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