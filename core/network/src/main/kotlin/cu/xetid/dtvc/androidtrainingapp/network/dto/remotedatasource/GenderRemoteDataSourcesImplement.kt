package cu.xetid.dtvc.androidtrainingapp.network.dto.remotedatasource


import cu.xetid.dtvc.androidtrainingapp.domain.datasourse.remote.GenderRemoteDataSources

import cu.xetid.dtvc.androidtrainingapp.model.dto.Gender
import cu.xetid.dtvc.androidtrainingapp.network.dto.mapper.toGender
import cu.xetid.dtvc.androidtrainingapp.network.dto.service.GenderApi
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class GenderRemoteDataSourcesImplement @Inject constructor(
    private val genderApi: GenderApi,
) : GenderRemoteDataSources, RemoteDatasourceAbstraction() {

    override suspend fun fetchGenderUser(name: String): ResultValue<Gender> {
        val resultValue: ResultValue<Gender> = safeApiCall {
            genderApi.fetchGenderUser(name).toGender()
        }
        return super.checkError(resultValue) as ResultValue<Gender>
    }
}