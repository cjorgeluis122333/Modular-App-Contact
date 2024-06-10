package cu.xetid.dtvc.androidtrainingapp.network.dto.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PostResponseDTO(
    @SerializedName("success") val isSuccess: Boolean,
    @SerializedName("message") val message: String?
) : Parcelable