package cu.xetid.dtvc.androidtrainingapp.model.dto


data class Gender(
    val count: Int,
    val gender: String,
    val name: String?,
    val probability: Float
)
/*
fun Gender.toEntity(): GenderEntity =
    GenderEntity(name = name!!, gender = gender, count = count, probability = probability)

fun Gender.toResponse(): GenderUserResponse =
    GenderUserResponse(name = name?:"", gender = gender?:"", count = count?:-2, probability = probability?:-2f)*/