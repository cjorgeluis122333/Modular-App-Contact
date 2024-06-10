package cu.xetid.dtvc.androidtrainingapp.network.dto.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MetaDTO(
    @SerializedName("current_page") val currentPage: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("last_page") val lastPage: Int
) : Parcelable
