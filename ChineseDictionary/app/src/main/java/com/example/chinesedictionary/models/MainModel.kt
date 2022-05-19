package com.example.chinesedictionary.models

data class MainModel (
    val result: ArrayList<Result>
) {
    data class Result (val _id: String, val name: String, val quantity: Int)
}