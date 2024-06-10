package cu.xetid.dtvc.androidtrainingapp.network.datasource

import cu.xetid.dtvc.androidtrainingapp.domain.datasource.remote.RemoteAuthDataSource
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.model.idp.IdentityProvider
import cu.xetid.dtvc.androidtrainingapp.model.network.PostResponseModel
import cu.xetid.dtvc.androidtrainingapp.model.oauth.AuthBearerTokenModel
import cu.xetid.dtvc.androidtrainingapp.network.dto.response.idp.IdentityProviderConstants
import cu.xetid.dtvc.androidtrainingapp.network.mapper.asModel
import cu.xetid.dtvc.androidtrainingapp.network.service.IDPService
import cu.xetid.dtvc.androidtrainingapp.network.service.OAuthService
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
internal class RemoteAuthDataSourceImpl @Inject constructor(
    private val oauthService: OAuthService,
    private val idpService: IDPService,
): RemoteAuthDataSource, RemoteDatasourceAbstraction() {

    override suspend fun login(email: String, password: String): ResultValue<AuthBearerTokenModel> {
        val resultValue: ResultValue<AuthBearerTokenModel> = safeApiCall {
            oauthService.postLogin(userName = email, password = password).asModel()
        }
        return super.checkError(resultValue) as ResultValue<AuthBearerTokenModel>
    }

    override suspend fun recoveryPassword(email: String): ResultValue<PostResponseModel> {
        val resultValue: ResultValue<PostResponseModel> = safeApiCall {
            oauthService.recoveryPassword(email = email).asModel()
        }
        return super.checkError(resultValue) as ResultValue<PostResponseModel>
    }

    override suspend fun refreshToken(refreshToken: String): ResultValue<AuthBearerTokenModel> {
        val resultValue: ResultValue<AuthBearerTokenModel> = safeApiCall {
            oauthService.refreshToken(refreshToken = refreshToken).asModel()
        }
        return super.checkError(resultValue) as ResultValue<AuthBearerTokenModel>
    }

    override suspend fun revokeToken(oldToken: String): ResultValue<Boolean> {
        val resultValue: ResultValue<Boolean> = safeApiCall {
            oauthService.revokeToken(oldToken = oldToken).isSuccessful
        }
        return super.checkError(resultValue) as ResultValue<Boolean>
    }

    override suspend fun getIdentityProviders(): ResultValue<List<IdentityProvider>> {
        val resultValue: ResultValue<List<IdentityProvider>> = safeApiCall {
            idpService.getIDPsActives().providers?.mapNotNull {
                if (it.status == IdentityProviderConstants.IDP_ACTIVE) {
                    when (it.provider) {
                        IdentityProviderConstants.LOGIN_TICKET -> IdentityProvider.Ticket
                        IdentityProviderConstants.LOGIN_EZ -> IdentityProvider.EZ
                        IdentityProviderConstants.LOGIN_CUBA_GOB -> IdentityProvider.CubaGob
                        else -> null
                    }
                } else null
            }.orEmpty()
        }
        return super.checkError(resultValue) as ResultValue<List<IdentityProvider>>
    }
}
