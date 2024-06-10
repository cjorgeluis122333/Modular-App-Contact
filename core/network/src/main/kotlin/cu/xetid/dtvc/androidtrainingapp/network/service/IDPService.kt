package cu.xetid.dtvc.androidtrainingapp.network.service

import cu.xetid.dtvc.androidtrainingapp.common.URL_GET_CHECK_IDP
import cu.xetid.dtvc.androidtrainingapp.network.dto.response.idp.IDPResponseDTO
import retrofit2.http.GET

interface IDPService {

    @GET(URL_GET_CHECK_IDP)
    suspend fun getIDPsActives(): IDPResponseDTO

}
