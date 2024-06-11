package com.example.contactnotmodular.core.domain.usecase.gender

import com.example.contactnotmodular.core.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.domain.repository.GenderRepository

import cu.xetid.dtvc.androidtrainingapp.model.dto.Gender
import javax.inject.Inject

class GenderApiUsesCase @Inject constructor(private val genderRepository: GenderRepository) {

    suspend operator fun invoke(userName: String): ResultValue<Gender?> {

        return when (val result = genderRepository.fetchGenderUser(userName)) {
            is ResultValue.Error -> {
                ResultValue.Error(result.exception)
            }

            is ResultValue.Success -> {
                ResultValue.Success(result.data)
            }
        }

    }
}