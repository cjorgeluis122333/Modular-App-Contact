package cu.xetid.dtvc.androidtrainingapp.domain.datasource.remote

import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.model.idp.IdentityProvider
import cu.xetid.dtvc.androidtrainingapp.model.network.PostResponseModel
import cu.xetid.dtvc.androidtrainingapp.model.oauth.AuthBearerTokenModel

interface RemoteAuthDataSource : RemoteDataSource {
    suspend fun login(email: String, password: String): ResultValue<AuthBearerTokenModel>
    suspend fun recoveryPassword(email: String): ResultValue<PostResponseModel>
    suspend fun refreshToken(refreshToken: String): ResultValue<AuthBearerTokenModel>
    suspend fun revokeToken(oldToken: String): ResultValue<Boolean>
    suspend fun getIdentityProviders(): ResultValue<List<IdentityProvider>>

}