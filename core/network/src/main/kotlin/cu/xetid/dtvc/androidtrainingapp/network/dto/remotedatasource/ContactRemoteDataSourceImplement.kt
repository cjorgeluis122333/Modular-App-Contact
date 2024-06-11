package cu.xetid.dtvc.androidtrainingapp.network.dto.remotedatasource

import cu.xetid.dtvc.androidtrainingapp.domain.datasourse.remote.ContactRemoteDataSource
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactLocationProjection
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactNameProjection
import cu.xetid.dtvc.androidtrainingapp.model.projection.ContactPictureProjection
import cu.xetid.dtvc.androidtrainingapp.network.dto.mapper.toModelAsProjectionLocation
import cu.xetid.dtvc.androidtrainingapp.network.dto.mapper.toModelAsProjectionName
import cu.xetid.dtvc.androidtrainingapp.network.dto.mapper.toModelAsProjectionPicture
import cu.xetid.dtvc.androidtrainingapp.network.dto.service.ContactApiService
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ContactRemoteDataSourceImplement @Inject constructor(
    private val contactApiService: ContactApiService
) : RemoteDatasourceAbstraction(), ContactRemoteDataSource {
    override suspend fun getUserName(): ResultValue<ContactNameProjection> {
        val resultValueName: ResultValue<ContactNameProjection> = safeApiCall {
            contactApiService.getUserName().toModelAsProjectionName()
        }
        return super.checkError(resultValueName) as ResultValue<ContactNameProjection>

    }

    override suspend fun getUserLocation(): ResultValue<ContactLocationProjection> {
        val resultValueLocation: ResultValue<ContactLocationProjection> = safeApiCall {
            contactApiService.getUserLocation().toModelAsProjectionLocation()
        }
        return super.checkError(resultValueLocation) as ResultValue<ContactLocationProjection>
    }

    override suspend fun getUserPicture(): ResultValue<ContactPictureProjection> {
        val resultValuePicture: ResultValue<ContactPictureProjection> = safeApiCall {
            contactApiService.getUserPicture().toModelAsProjectionPicture()
        }
        return super.checkError(resultValuePicture) as ResultValue<ContactPictureProjection>

    }


}