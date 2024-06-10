package cu.xetid.dtvc.androidtrainingapp.network.dto.response.auth

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthBearerTokenResponseDTO(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("scope") val scope: String?,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("expires_in") val expiresIn: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("id_token") val tokenId: String
) : Parcelable
