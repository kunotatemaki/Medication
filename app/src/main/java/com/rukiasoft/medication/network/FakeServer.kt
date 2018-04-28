package com.rukiasoft.medication.network

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.rukiasoft.codewars.network.ApiResponse
import com.rukiasoft.medication.R
import com.rukiasoft.medication.repository.MedicationServerResponse
import com.rukiasoft.medication.utils.ResourcesManager
import javax.inject.Inject

class FakeServer @Inject constructor(private val resourcesManager: ResourcesManager){

    fun getMedications(): LiveData<ApiResponse<MedicationServerResponse>>{

        //create fake successful response
        val response = Response<MedicationServerResponse>()
        response.code = 200
        val list: MutableList<String> = mutableListOf()
        //get strings from file
        val myString = resourcesManager.getStringArray(R.array.medications)
        list.addAll(myString)
        val server = MedicationServerResponse(list)
        response.body = server
        response.isSuccessful = true
        val apiResponse = ApiResponse(response)

        //return the strings wrapped in a live data
        val resp: MutableLiveData<ApiResponse<MedicationServerResponse>> = MutableLiveData()
        resp.value = apiResponse

        //wait a few seconds -> 5
        Thread.sleep(5000)

        return resp
    }

}