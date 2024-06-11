package com.example.contactnotmodular.core.domain.datasourse.local

import cu.xetid.dtvc.androidtrainingapp.model.dto.Gender
import kotlinx.coroutines.flow.Flow

interface GenderLocalDataSorcerer {

    //fun selectAllUserGender(): Flow<List<GenderEntity>>

    fun getSpecificUserByName(name: String): Flow<Gender?>

    suspend fun insertListOfUserEntry(userEntityToSave: List<Gender>)

    suspend fun insertUserEntry(userEntityToSave: Gender)

}