package cu.xetid.dtvc.androidtrainingapp.domain.datasource.local

import cu.xetid.dtvc.androidtrainingapp.model.idp.IdentityProvider
import kotlinx.coroutines.flow.Flow

interface LocalAuthDatasource {
    suspend fun saveAccessToken(accessToken: String)
    fun getAccessToken(): String?
    suspend fun saveRefreshToken(refreshToken: String)
    fun getRefreshToken(): String?
    suspend fun clearToken()
    suspend fun getIdentityProvidersActive(): Flow<List<IdentityProvider>>
    suspend fun getIdentityProviderAccess(): Flow<IdentityProvider>
    suspend fun saveIdentityProviders(providers: List<IdentityProvider>)
    suspend fun saveIdentityProviderAccess(provider: IdentityProvider)
}