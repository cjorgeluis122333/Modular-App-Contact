package cu.xetid.dtvc.androidtrainingapp.network.di

import cu.xetid.dtvc.androidtrainingapp.common.security.CoreRetrofit
import cu.xetid.dtvc.androidtrainingapp.network.service.IDPService
import cu.xetid.dtvc.androidtrainingapp.network.service.OAuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ServicesModule {

    @Provides
    fun provideLoginService(@CoreRetrofit retrofit: Retrofit): OAuthService =
        retrofit.create(OAuthService::class.java)

    @Provides
    fun provideIDPService(@CoreRetrofit retrofit: Retrofit): IDPService =
        retrofit.create(IDPService::class.java)

}
