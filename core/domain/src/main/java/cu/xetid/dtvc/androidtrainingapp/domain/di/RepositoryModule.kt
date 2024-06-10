package cu.xetid.dtvc.androidtrainingapp.domain.di

import cu.xetid.dtvc.androidtrainingapp.domain.data.AuthRepositoryImpl
import cu.xetid.dtvc.androidtrainingapp.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Singleton
    @Binds
    fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository

}
