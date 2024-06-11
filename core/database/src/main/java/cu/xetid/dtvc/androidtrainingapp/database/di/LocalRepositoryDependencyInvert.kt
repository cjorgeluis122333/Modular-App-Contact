package cu.xetid.dtvc.androidtrainingapp.database.di

import cu.xetid.dtvc.androidtrainingapp.database.datasource.ContactLocalDataSourceImplement
import cu.xetid.dtvc.androidtrainingapp.database.datasource.GenderLocalDataSorcererImplement
import cu.xetid.dtvc.androidtrainingapp.domain.datasourse.local.ContactLocalDataSource
import cu.xetid.dtvc.androidtrainingapp.domain.datasourse.local.GenderLocalDataSorcerer
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalRepositoryDependencyInvert {

    @Singleton
    @Binds
    abstract fun provideLocalRepository(localDataSorcererReciprocity: GenderLocalDataSorcererImplement): GenderLocalDataSorcerer

    @Singleton
    @Binds
    abstract fun provideContactLocalRepository(contactLocalDataSorcererReciprocity: ContactLocalDataSourceImplement): ContactLocalDataSource

}