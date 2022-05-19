package com.example.chinesedictionary.models

data class ExampleModel (
    val result: ArrayList<Result>
) {
    data class Result (val characters: String,
                       val pinyin: String,
                       val translation: String,
    )
}