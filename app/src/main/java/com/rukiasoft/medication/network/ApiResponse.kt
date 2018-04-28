package com.rukiasoft.codewars.network

import com.rukiasoft.medication.network.Response

/**
 * Created by Roll on 4/10/17.
 * Common class used by API responses.
 * @param <T>
 */

class ApiResponse<T> {
    private val code: Int
    val body: T?
    val errorMessage: String?
    fun isSuccessful(): Boolean = code in 200..299

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            errorMessage = null
        } else {
            errorMessage = response.errorBody
            body = null
        }

    }



}
