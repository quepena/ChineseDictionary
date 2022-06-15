package com.example.chinesedictionary.retrofit

import com.example.chinesedictionary.models.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {

    @GET("themes")
    fun data(): Call<MainModel>

    @GET("themes/{intentId}/words")
    fun dataWords(@Path("intentId") intentId: String): Call<WordsModel>

    @GET("words/{intentId}")
    fun dataWord(@Path("intentId") intentId: String): Call<WordModel>

    @GET("words/{intentId}/examples")
    fun dataExamples(@Path("intentId") intentId: String): Call<ExampleModel>

    @GET("words/search")
    fun dataSearch(@Query("keyword") keyword: String): Call<WordsModel>
}