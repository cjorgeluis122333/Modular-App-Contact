package com.example.contactnotmodular.core.domain.datasourse.remote


import com.example.contactnotmodular.core.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.domain.datasourse.remote.RemoteDataSource
import cu.xetid.dtvc.androidtrainingapp.model.dto.Gender

interface GenderRemoteDataSources : RemoteDataSource {

    suspend fun fetchGenderUser(name: String): ResultValue<Gender?>

}
