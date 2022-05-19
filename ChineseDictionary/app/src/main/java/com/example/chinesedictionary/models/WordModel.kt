package com.example.chinesedictionary.models

data class WordModel (
    val result: Result
) {
    data class Result (val themeId: ArrayList<String>,
                       val character: String,
                       val pinyin: String,
                       val translation: String,
    )
}