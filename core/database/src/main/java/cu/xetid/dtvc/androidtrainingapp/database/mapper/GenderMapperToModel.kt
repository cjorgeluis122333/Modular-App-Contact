package cu.xetid.dtvc.androidtrainingapp.database.mapper

import cu.xetid.dtvc.androidtrainingapp.database.entity.GenderEntity
import cu.xetid.dtvc.androidtrainingapp.model.dto.Gender


 internal fun GenderEntity.toGender(): Gender = Gender(name = name, gender = gender, count = count, probability = probability)
internal fun Gender.toGenderEntity(): GenderEntity = GenderEntity(name = name!!, gender = gender, count = count, probability = probability)