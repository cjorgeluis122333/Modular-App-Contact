package com.example.contactnotmodular.core.domain.repository

import com.example.contactnotmodular.core.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.model.dto.Gender
import kotlinx.coroutines.flow.Flow

interface GenderRepository {

    fun selectSpecificUserGender(userName:String): Flow<Gender?>
   suspend fun fetchGenderUser(userName: String): ResultValue<Gender?>


}