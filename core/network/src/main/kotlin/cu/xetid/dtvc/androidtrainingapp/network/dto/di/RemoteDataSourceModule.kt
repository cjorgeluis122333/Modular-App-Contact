package cu.xetid.dtvc.androidtrainingapp.network.dto.di

import cu.xetid.dtvc.androidtrainingapp.network.dto.service.ContactApiService
import cu.xetid.dtvc.androidtrainingapp.network.dto.service.GenderApi
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
object RemoteDataSourceModule {

//Gender
    @Singleton
    @Provides
    @Named("BaseUrlGender")
    fun urlApiGender() = "https://api.genderize.io/"

    @Singleton
    @Provides
    fun InstancesRetrofit(@Named("BaseUrlGender") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

    }

    @Singleton
    @Provides
    fun InstanceWebbSevis(retrofit: Retrofit): GenderApi {
        return retrofit.create(GenderApi::class.java)
    }

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