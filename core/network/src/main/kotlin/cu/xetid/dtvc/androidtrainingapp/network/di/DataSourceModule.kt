package cu.xetid.dtvc.androidtrainingapp.network.di

import cu.xetid.dtvc.androidtrainingapp.domain.datasource.remote.RemoteAuthDataSource
import cu.xetid.dtvc.androidtrainingapp.network.datasource.RemoteAuthDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {

    @Singleton
    @Binds
    fun bindsRemoteAuthDataSource(
        impl: RemoteAuthDataSourceImpl
    ): RemoteAuthDataSource

}
