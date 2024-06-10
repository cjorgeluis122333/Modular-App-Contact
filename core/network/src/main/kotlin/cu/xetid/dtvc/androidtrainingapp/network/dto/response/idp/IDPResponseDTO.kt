package cu.xetid.dtvc.androidtrainingapp.network.dto.response.idp

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class IDPResponseDTO(
    @SerializedName("data") val providers : List<IDPResponse>? = null
): Parcelable

@Parcelize
data class IDPResponse(
    @SerializedName("idp") val provider : String,
    @SerializedName("status") var status: String,
): Parcelable
