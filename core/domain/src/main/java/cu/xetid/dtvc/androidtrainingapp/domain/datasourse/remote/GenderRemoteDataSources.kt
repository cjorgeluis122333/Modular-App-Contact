package cu.xetid.dtvc.androidtrainingapp.domain.datasourse.remote


import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.model.dto.Gender


interface GenderRemoteDataSources : RemoteDataSource {

    suspend fun fetchGenderUser(name: String): ResultValue<Gender?>

}
