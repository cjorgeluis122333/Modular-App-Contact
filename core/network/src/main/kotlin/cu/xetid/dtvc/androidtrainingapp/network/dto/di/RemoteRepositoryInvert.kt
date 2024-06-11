package cu.xetid.dtvc.androidtrainingapp.network.dto.di

import cu.xetid.dtvc.androidtrainingapp.domain.datasourse.remote.ContactRemoteDataSource
import cu.xetid.dtvc.androidtrainingapp.domain.datasourse.remote.GenderRemoteDataSources
import cu.xetid.dtvc.androidtrainingapp.network.dto.remotedatasource.ContactRemoteDataSourceImplement
import cu.xetid.dtvc.androidtrainingapp.network.dto.remotedatasource.GenderRemoteDataSourcesImplement
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteRepositoryInvert {

    @Binds
    @Singleton
    abstract fun invertDependency(remoteDataSourcesRepository: GenderRemoteDataSourcesImplement): GenderRemoteDataSources

    @Binds
    @Singleton
    abstract fun invertRemoteContactDependency(remoteDataSourcesRepository: ContactRemoteDataSourceImplement): ContactRemoteDataSource

}