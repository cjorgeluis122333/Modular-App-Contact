package cu.xetid.dtvc.androidtrainingapp.network.dto.service

import cu.xetid.dtvc.androidtrainingapp.network.dto.response.GenderUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GenderApi {
    @GET("/")
   suspend fun fetchGenderUser(@Query("name") name: String): GenderUserResponse
}