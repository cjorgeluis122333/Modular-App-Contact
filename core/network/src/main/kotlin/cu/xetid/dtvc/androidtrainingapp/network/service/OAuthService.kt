package cu.xetid.dtvc.androidtrainingapp.network.service

import cu.xetid.dtvc.androidtrainingapp.network.dto.response.PostResponseDTO
import cu.xetid.dtvc.androidtrainingapp.network.dto.response.auth.AuthBearerTokenResponseDTO
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

internal interface OAuthService {

    @FormUrlEncoded
    @POST("/oauth2/token")
    suspend fun postLogin(
        @Field("scope") clientId: String = "openid",
        @Field("username") userName: String,
        @Field("password") password: String,
        @Field("grant_type") grantType: String = "password",
    ): AuthBearerTokenResponseDTO

    @FormUrlEncoded
    @POST("/api/email-change-password")
    suspend fun recoveryPassword(
        @Field("email") email: String,
    ): PostResponseDTO


    @FormUrlEncoded
    @POST("/oauth2/token")
    suspend fun refreshToken(
        @Field("grant_type") grantType: String = "refresh_token",
        @Field("refresh_token") refreshToken: String,
    ): AuthBearerTokenResponseDTO

    @FormUrlEncoded
    @POST("/oauth2/revoke")
    suspend fun revokeToken(
        @Field("token") oldToken: String?,
        @Field("token_type_hint") tokenType: String = "access_token",
    ): Response<Void?>

}
