package com.example.chinesedictionary.models

data class WordsModel (
    val result: ArrayList<Result>
) {
    data class Result (val _id: String,
                       val themeId: ArrayList<String>,
                       val character: String,
                       val pinyin: String,
                       val translation: String,
                       val text: String,
    )
}