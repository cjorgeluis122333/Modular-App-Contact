package cu.xetid.dtvc.androidtrainingapp.network.service

import cu.xetid.dtvc.androidtrainingapp.domain.datasource.local.LocalAuthDatasource
import dagger.Lazy
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

internal class TokenAuthenticator @Inject constructor(
    private val service: Lazy<InterceptService>,
    private val localAuthDatasource: LocalAuthDatasource
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        countFails+=1
        if(countFails== MAX_REFRESH_FAILS_COUNT){
            runBlocking {
                localAuthDatasource.saveAccessToken("")
                localAuthDatasource.saveRefreshToken("")
            }
            response.request
        }
        if (response.code == 401) {
            val refreshToken = localAuthDatasource.getRefreshToken()
            if (!refreshToken.isNullOrBlank()) {
                val result = service.get().refreshBearerToken(
                    refreshToken = refreshToken,
                )
                try {
                    val responseCall = result.execute()
                    if (responseCall.isSuccessful && responseCall.body() != null) {
                        val authBearerToken = responseCall.body()!!
                        runBlocking {
                            localAuthDatasource.saveAccessToken(authBearerToken.accessToken)
                            localAuthDatasource.saveRefreshToken(authBearerToken.refreshToken)
                        }
                        isTokenUpdate = true
                        countFails = 0
                    } else {
                        countFails++
                    }
                } catch (e: Exception) {
                    isTokenUpdate = false
                }
            } else {
                isTokenUpdate = false
            }
        }
        return when {
            isTokenUpdate -> response.request.newBuilder().build()
            else -> null
        }
    }

    companion object {
        private const val MAX_REFRESH_FAILS_COUNT = 2
        var isTokenUpdate = false
        var countFails = 0
    }

}
