package com.example.chinesedictionary

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chinesedictionary.adapters.ThemeAdapter
import com.example.chinesedictionary.adapters.WordAdapter
import com.example.chinesedictionary.databinding.ActivityMainBinding
import com.example.chinesedictionary.models.MainModel
import com.example.chinesedictionary.models.WordsModel
import com.example.chinesedictionary.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"

    private lateinit var connectionLiveData: ConnectionLiveData

    private lateinit var binding : ActivityMainBinding

    private lateinit var themeAdapter: ThemeAdapter

    private lateinit var wordAdapter: WordAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.title = "Chinese Dictionary"

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
                    getDataFromApi()

                    binding.searchEditText.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable) {}
                        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
                        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                            if(s.toString() == "") {
                                setupRecyclerView()
                                getDataFromApi()
                            } else {
                                setupRecyclerViewSearch()
                                getDataSearch(s.toString())
                            }
                        }
                    })

                    if(binding.searchEditText.text.toString() != "") {
                        setupRecyclerViewSearch()
                        getDataSearch(binding.searchEditText.text.toString())
                    }
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
        themeAdapter = ThemeAdapter(arrayListOf(), object : ThemeAdapter.OnAdapterListener {
            override fun onClick(result: MainModel.Result) {
                startActivity(
                    Intent(this@MainActivity, WordsActivity::class.java)
                        .putExtra("intent_title", result.name)
                        .putExtra("intent_id", result._id)
                )
            }
        })

        binding.recyclerView.apply {
            binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.recyclerView.adapter = themeAdapter
        }
    }

    private fun setupRecyclerViewSearch(){
        wordAdapter = WordAdapter(arrayListOf(), object : WordAdapter.OnAdapterListener {
            override fun onClick(result: WordsModel.Result) {
                startActivity(
                    Intent(this@MainActivity, DetailActivity::class.java)
                        .putExtra("intent_title", result.character)
                        .putExtra("intent_id", result._id)
                )
            }
        })

        binding.recyclerView.apply {
            binding.recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            binding.recyclerView.adapter = wordAdapter
        }
    }

    private fun getDataFromApi(){
        showLoading(true)
        ApiService.endpoint.data()
            .enqueue(object : Callback<MainModel> {
                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    printLog( t.toString() )
                    showLoading(false)
                }
                override fun onResponse(
                    call: Call<MainModel>,
                    response: Response<MainModel>
                ) {
                    showLoading(false)
                    if (response.isSuccessful) {
                        showResult( response.body()!! )
                    }
                }
            })
    }

    private fun getDataSearch(keyword : String) {
        ApiService.endpoint.dataSearch(keyword)
            .enqueue(object : Callback<WordsModel> {
                override fun onFailure(call: Call<WordsModel>, t: Throwable) {
                    printLog( t.toString() )
                    showLoading(false)
                }
                override fun onResponse(
                    call: Call<WordsModel>,
                    response: Response<WordsModel>
                ) {
                    showLoading(false)
                    if (response.isSuccessful) {
                        showResultSearch( response.body()!! )
                    }
                }
            })
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

    private fun showResult(results: MainModel) {
        for (result in results.result) printLog( "title: ${result.name}" )
        themeAdapter.setData( results.result )
    }

    private fun showResultSearch(results: WordsModel) {
        wordAdapter.setData( results.result )
    }
}
