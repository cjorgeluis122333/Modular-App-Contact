package cu.xetid.dtvc.androidtrainingapp.domain.repository

import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.model.idp.IdentityProvider
import cu.xetid.dtvc.androidtrainingapp.model.network.PostResponseModel
import cu.xetid.dtvc.androidtrainingapp.model.oauth.AuthBearerTokenModel
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun singIn(email: String, password: String): ResultValue<AuthBearerTokenModel>
    suspend fun recoveryPassword(email: String): ResultValue<PostResponseModel>
    suspend fun refreshToken(refreshToken: String): ResultValue<AuthBearerTokenModel>
    suspend fun getIdentityProviders(): ResultValue<List<IdentityProvider>>
    suspend fun getStoredIDP(): Flow<List<IdentityProvider>>
    suspend fun getActiveIDP(): Flow<IdentityProvider>
    suspend fun saveIDPs(providers: List<IdentityProvider>)
    suspend fun saveActiveIDP(provider: IdentityProvider)
    suspend fun saveAccessToken(accessToken: String)
    suspend fun getAccessToken(): String?
    suspend fun saveRefreshToken(refreshToken: String)
    suspend fun getRefreshToken(): String?
    suspend fun revokeToken(): ResultValue<Boolean>
    suspend fun clearToken()

}
