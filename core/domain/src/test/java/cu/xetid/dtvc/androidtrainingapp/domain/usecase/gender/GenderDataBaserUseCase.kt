package com.example.contactnotmodular.core.domain.usecase.gender

import cu.xetid.dtvc.androidtrainingapp.domain.repository.GenderRepository
import cu.xetid.dtvc.androidtrainingapp.model.dto.Gender
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GenderDataBaserUseCase @Inject constructor(
    private val genderRepository: GenderRepository
) {
   operator fun invoke(name: String): Flow<Gender?> {
        return genderRepository.selectSpecificUserGender(name)
    }

}