package cu.xetid.dtvc.androidtrainingapp.domain.di

import cu.xetid.dtvc.androidtrainingapp.domain.data.ContactRepositoryImplement
import cu.xetid.dtvc.androidtrainingapp.domain.data.GenderRepositoryImplement
import cu.xetid.dtvc.androidtrainingapp.domain.repository.ContactRepository
import cu.xetid.dtvc.androidtrainingapp.domain.repository.GenderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GenderRepositoryInvert {

    @Singleton
    @Binds
    abstract fun invertGenderRepository(genderRepositoryImplement: GenderRepositoryImplement): GenderRepository

    @Singleton
    @Binds
    abstract fun invertContactRepository(contactRepositoryImplement: ContactRepositoryImplement): ContactRepository

}