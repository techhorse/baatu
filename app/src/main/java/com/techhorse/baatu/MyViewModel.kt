package com.techhorse.baatu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.techhorse.baatu.data.remoterepository.RemoteRepository

class MyViewModelFactory constructor(private val remoteRepository: RemoteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(FetchViewModel::class.java)) {
            FetchViewModel(this.remoteRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}