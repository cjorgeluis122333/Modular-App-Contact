package cu.xetid.dtvc.androidtrainingapp.domain.data

import cu.xetid.dtvc.androidtrainingapp.domain.datasourse.local.GenderLocalDataSorcerer
import cu.xetid.dtvc.androidtrainingapp.domain.datasourse.remote.GenderRemoteDataSources
import cu.xetid.dtvc.androidtrainingapp.domain.repository.GenderRepository
import cu.xetid.dtvc.androidtrainingapp.common.coroutine.IoDispatcher
import cu.xetid.dtvc.androidtrainingapp.model.ResultValue
import cu.xetid.dtvc.androidtrainingapp.model.dto.Gender
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GenderRepositoryImplement @Inject constructor(
    private val genderRemoteDataSources: GenderRemoteDataSources,
    private val localDataSourcesRepository: GenderLocalDataSorcerer,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : GenderRepository {

    override fun selectSpecificUserGender(userName: String): Flow<Gender?> =
        localDataSourcesRepository.getSpecificUserByName(name = userName).flowOn(ioDispatcher)


    override suspend fun fetchGenderUser(userName: String): ResultValue<Gender?> {
        return when (val resultValue = genderRemoteDataSources.fetchGenderUser(userName)) {
            is ResultValue.Error -> {
                ResultValue.Error(resultValue.exception)
            }

            is ResultValue.Success -> {
                resultValue.data
                    .let {
                        if (it!=null) {localDataSourcesRepository.insertUserEntry(it)}
                    }
                ResultValue.Success(resultValue.data)
            }
        }
    }


}


/*
 @Throws(IOException::class, Exception::class)
 override fun selectSpecificUserGender(userName: String): Flow<Gender> {
         return localDataSourcesRepository.getSpecificUserByName(name = userName).map {
             it.toGender()
         }.retryWhen { cause, attempt ->
             if (cause is NullPointerException && attempt < 1) {
                 when (val result = searchGenderAtApi(userName)){
                     is ResultValue.Error -> {
                         false
                     }
                     is ResultValue.Success -> {
                         result.data
                     }
                 }
             } else {
                 false
             }
         }.take(1)

 }


 @Throws(IOException::class, Exception::class)
 private suspend fun searchGenderAtApi(name: String): ResultValue<Boolean> {
     return when(val resultValue = genderRemoteDataSources.fetchGenderUser(name)){
         is ResultValue.Error -> {
             resultValue
         }
         is ResultValue.Success -> {
             resultValue.data.toEntity()
                 .let { localDataSourcesRepository.insertUserEntry(it) }
             ResultValue.Success(true)
         }
     }
 }


*/
