package com.example.contactnotmodular.core.domain.datasourse.remote

import com.example.contactnotmodular.core.model.ResultValue


interface RemoteDataSource {

    suspend fun <T> safeApiCall(request: suspend () -> T): ResultValue<T> = try {
        val response = request.invoke()
        ResultValue.Success(response)
    } catch (e: Exception) {
        ResultValue.Error(e)
    }

}
