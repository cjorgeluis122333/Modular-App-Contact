package cu.xetid.dtvc.androidtrainingapp.datastore.di

import cu.xetid.dtvc.androidtrainingapp.datastore.datasource.LocalAuthDatasourceImpl
import cu.xetid.dtvc.androidtrainingapp.domain.datasource.local.LocalAuthDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataStoreModule {

    @Singleton
    @Binds
    fun bindLocalAuthDataSource(impl: LocalAuthDatasourceImpl): LocalAuthDatasource

}
