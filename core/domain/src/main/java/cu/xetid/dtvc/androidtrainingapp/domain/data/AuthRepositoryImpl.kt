package cu.xetid.dtvc.androidtrainingapp.domain.data

import cu.xetid.dtvc.androidtrainingapp.common.coroutine.IoDispatcher
import cu.xetid.dtvc.androidtrainingapp.domain.datasource.local.LocalAuthDatasource
import cu.xetid.dtvc.androidtrainingapp.domain.datasource.remote.RemoteAuthDataSource
import cu.xetid.dtvc.androidtrainingapp.domain.repository.AuthRepository
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.model.idp.IdentityProvider
import cu.xetid.dtvc.androidtrainingapp.model.network.PostResponseModel
import cu.xetid.dtvc.androidtrainingapp.model.oauth.AuthBearerTokenModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val localAuthDatasource: LocalAuthDatasource,
    private val remoAuthDatasource: RemoteAuthDataSource
) : AuthRepository {

    override suspend fun singIn(
        email: String, password: String
    ): ResultValue<AuthBearerTokenModel> =
        withContext(dispatcher) { remoAuthDatasource.login(email, password) }

    override suspend fun recoveryPassword(email: String): ResultValue<PostResponseModel> =
        withContext(dispatcher) {
            remoAuthDatasource.recoveryPassword(email)
        }

    override suspend fun refreshToken(refreshToken: String): ResultValue<AuthBearerTokenModel> =
        withContext(dispatcher) {
            remoAuthDatasource.refreshToken(refreshToken)
        }

    override suspend fun getIdentityProviders(): ResultValue<List<IdentityProvider>> =
        withContext(dispatcher) { remoAuthDatasource.getIdentityProviders() }

    override suspend fun getStoredIDP(): Flow<List<IdentityProvider>> =
        withContext(dispatcher) { localAuthDatasource.getIdentityProvidersActive() }

    override suspend fun getActiveIDP(): Flow<IdentityProvider> =
        withContext(dispatcher) { localAuthDatasource.getIdentityProviderAccess() }

    override suspend fun saveIDPs(providers: List<IdentityProvider>) =
        withContext(dispatcher) { localAuthDatasource.saveIdentityProviders(providers) }

    override suspend fun saveActiveIDP(provider: IdentityProvider) =
        withContext(dispatcher) { localAuthDatasource.saveIdentityProviderAccess(provider) }

    override suspend fun saveAccessToken(accessToken: String) =
        withContext(dispatcher) { localAuthDatasource.saveAccessToken(accessToken) }

    override suspend fun getAccessToken(): String? =
        withContext(dispatcher) { localAuthDatasource.getAccessToken() }

    override suspend fun saveRefreshToken(refreshToken: String) =
        withContext(dispatcher) { localAuthDatasource.saveRefreshToken(refreshToken) }

    override suspend fun getRefreshToken(): String? =
        withContext(dispatcher) { localAuthDatasource.getRefreshToken() }

    override suspend fun revokeToken(): ResultValue<Boolean> =
        withContext(dispatcher) {
            val oldToken = localAuthDatasource.getAccessToken() ?: ""
            val response = remoAuthDatasource.revokeToken(oldToken)
            localAuthDatasource.clearToken()
            return@withContext response
        }

    override suspend fun clearToken() =
        withContext(dispatcher) { localAuthDatasource.clearToken() }

}