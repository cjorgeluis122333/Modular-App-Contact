package cu.xetid.dtvc.androidtrainingapp.network.dto.di

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
object GenderRemoteDataSourceModule {
    /*               OJO cuando tengo dos retrofit se parte ala aplicacion
    //Gender
    @Singleton
    @Provides
    @Named("BaseUrlGender")
    fun urlApiGender() = "https://api.genderize.io/"

    @Singleton
    @Provides
    fun InstancesGenderRetrofit(@Named("BaseUrlGender") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

    }

    @Singleton
    @Provides
    fun InstanceGenderWebbSevis(retrofit: Retrofit): GenderApi {
        return retrofit.create(GenderApi::class.java)
    }

     */
}