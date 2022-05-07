package com.techhorse.baatu.data.remoterepository

import com.techhorse.baatu.RetrofitService

class RemoteRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllMovies() = retrofitService.getAllUsers()
}