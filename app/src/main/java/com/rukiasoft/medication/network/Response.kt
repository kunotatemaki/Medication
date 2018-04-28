package com.rukiasoft.medication.network

class Response<T> {
    var isSuccessful: Boolean = false
    var code: Int = 500
    var errorBody: String? = null
    var body: T? = null

    fun body() = body
    fun code() = code
}