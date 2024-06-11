package cu.xetid.dtvc.androidtrainingapp.network.dto.response




data class GenderUserResponse(
    val count: Int,
    val gender: String,
    val name: String?,
    val probability: Float
)
