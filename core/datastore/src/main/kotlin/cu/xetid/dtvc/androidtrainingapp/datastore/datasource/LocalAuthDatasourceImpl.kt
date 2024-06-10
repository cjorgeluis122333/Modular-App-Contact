package cu.xetid.dtvc.androidtrainingapp.datastore.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import cu.xetid.dtvc.androidtrainingapp.domain.datasource.local.LocalAuthDatasource
import cu.xetid.dtvc.androidtrainingapp.model.idp.IdentityProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class LocalAuthDatasourceImpl @Inject constructor(
    @ApplicationContext val context: Context
) : LocalAuthDatasource {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth_preference")

    private object Keys {
        val TOKEN_KEY = stringPreferencesKey("token_key")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_token_key")
        val IDENTITY_PROVIDER_ACCESS = stringPreferencesKey("identity_provider_access")
        val IDENTITY_PROVIDERS_ACTIVE = stringSetPreferencesKey("identity_providers_active")
    }

    override suspend fun clearToken() {
        context.dataStore.edit {
            it[Keys.TOKEN_KEY] = ""
            it[Keys.REFRESH_TOKEN_KEY] = ""}
    }

    override suspend fun saveAccessToken(accessToken: String) {
        context.dataStore.edit { it[Keys.TOKEN_KEY] = accessToken }
    }

    override fun getAccessToken(): String? = runBlocking {
        context.dataStore.data.firstOrNull()?.get(Keys.TOKEN_KEY)
    }

    override suspend fun saveRefreshToken(refreshToken: String) {
        context.dataStore.edit { it[Keys.REFRESH_TOKEN_KEY] = refreshToken }
    }

    override fun getRefreshToken(): String? = runBlocking {
        context.dataStore.data.firstOrNull()?.get(Keys.REFRESH_TOKEN_KEY)
    }

    override suspend fun getIdentityProvidersActive(): Flow<List<IdentityProvider>> =
        context.dataStore.data.map { preferences ->
            preferences[Keys.IDENTITY_PROVIDERS_ACTIVE]?.map { IdentityProvider.valueOf(it) }
                .orEmpty()
        }

    override suspend fun saveIdentityProviders(providers: List<IdentityProvider>) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[Keys.IDENTITY_PROVIDERS_ACTIVE] =
                providers.map { it.toString() }.toSet()
        }
    }

    override suspend fun saveIdentityProviderAccess(provider: IdentityProvider) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[Keys.IDENTITY_PROVIDER_ACCESS] = provider.toString()
        }
    }

    override suspend fun getIdentityProviderAccess(): Flow<IdentityProvider> =
        context.dataStore.data.map {preferences ->
            preferences[Keys.IDENTITY_PROVIDER_ACCESS]?.let {
                IdentityProvider.valueOf(it)
            } ?: IdentityProvider.Ticket
        }

}