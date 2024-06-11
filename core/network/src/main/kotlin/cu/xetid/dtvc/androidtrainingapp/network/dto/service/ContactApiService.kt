package cu.xetid.dtvc.androidtrainingapp.network.dto.service

import cu.xetid.dtvc.androidtrainingapp.network.dto.response.ContactResponse
import retrofit2.http.GET

interface ContactApiService {
    @GET("?inc=name")
    suspend fun getUserName(): ContactResponse

    @GET("?inc=location")
    suspend fun getUserLocation(): ContactResponse

    @GET("?inc=picture")
    suspend fun getUserPicture(): ContactResponse

}