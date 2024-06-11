package cu.xetid.dtvc.androidtrainingapp.network.dto.mapper

import cu.xetid.dtvc.androidtrainingapp.model.dto.Gender
import cu.xetid.dtvc.androidtrainingapp.network.dto.response.GenderUserResponse


fun GenderUserResponse.toGender(): Gender =
    Gender(name = name, gender = gender, count = count, probability = probability)

/**
 * I will have to implement de database module if i want uses UserEntry
 */
//fun GenderUserResponse.toEntity(): GenderEntity = GenderEntity(name = name.orEmpty(), gender = gender, count = count, probability = probability)