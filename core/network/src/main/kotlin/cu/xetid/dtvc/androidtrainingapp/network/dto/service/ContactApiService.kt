package cu.xetid.dtvc.androidtrainingapp.network.dto.service

import cu.xetid.dtvc.androidtrainingapp.network.dto.response.ContactResponse
import cu.xetid.dtvc.androidtrainingapp.network.dto.response.ContactResponseList
import retrofit2.http.GET

interface ContactApiService {
    @GET("?inc=name")
    suspend fun getUserName(): ContactResponseList

    @GET("?inc=location")
    suspend fun getUserLocation(): ContactResponseList

    @GET("?inc=picture")
    suspend fun getUserPicture(): ContactResponseList

}