package cu.xetid.dtvc.androidtrainingapp.network.service

import cu.xetid.dtvc.androidtrainingapp.network.dto.response.auth.AuthBearerTokenResponseDTO
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

internal interface InterceptService {

    @FormUrlEncoded
    @POST("/oauth2/token")
    fun refreshBearerToken(
        @Field("grant_type") grantType: String = "refresh_token",
        @Field("refresh_token") refreshToken: String?,
    ): Call<AuthBearerTokenResponseDTO?>
}
