package cu.xetid.dtvc.androidtrainingapp.network.dto.di

import cu.xetid.dtvc.androidtrainingapp.network.dto.service.ContactApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContactRemoteDataSourceModule {
/*

*/
    //Contact
    @Singleton
    @Provides
    @Named("BaseUrlContact")
    fun urlApiContact() = "https://randomuser.me/api/"

    @Singleton
    @Provides
    fun InstancesContactRetrofit(@Named("BaseUrlContact") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

    }

    @Singleton
    @Provides
    fun InstancesContactApiService(retrofit: Retrofit): ContactApiService {
        return retrofit.create(ContactApiService::class.java)
    }
}