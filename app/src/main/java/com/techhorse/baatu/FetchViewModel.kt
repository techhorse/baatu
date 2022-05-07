package com.techhorse.baatu

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.techhorse.baatu.model.UserDataModel
import com.techhorse.baatu.data.remoterepository.RemoteRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FetchViewModel constructor(private val remoteRepository: RemoteRepository)  : ViewModel()  {

    val userList = MutableLiveData<List<UserDataModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllUserList() {
        val response = remoteRepository.getAllMovies()
        response.enqueue(object : Callback<List<UserDataModel>> {
            override fun onResponse(call: Call<List<UserDataModel>>, response: Response<List<UserDataModel>>) {
                Log.d(TAG, "Abhishek Succesful Response in View Model ${response.body().toString()}")
                userList.postValue(response.body())
            }
            override fun onFailure(call: Call<List<UserDataModel>>, t: Throwable) {
                Log.d(TAG, "Abhishek Failure Response in View Model")
                errorMessage.postValue(t.message)
            }
        })
    }
}